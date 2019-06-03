package kaltura.kaltura.restapi.client;


import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


import com.google.common.net.HttpHeaders;

import kaltura.kaltura.restapi.ConsoleLogger;
import kaltura.kaltura.restapi.RestCall;

public class RestClient {

	public Client restClient;
	private WebTarget rootTarget;
	private WebTarget currentTarget;
	private Response response;
	private String responseString;
	String restURL;
	String restAuth;
	private String clientSecret;
	String jenkinsClientSecret;

	private Form form = new Form();

	private MediaType mediaType = MediaType.APPLICATION_JSON_TYPE;
	private String requestPayload;

	public enum HttpMethod {
		GET, PUT, DELETE, POST;
	}

	public enum ParameterType {
		PATH, QUERY, FORM;
	}

	public RestClient(String rootContextUrl) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);
		restClient = ClientBuilder.newClient(clientConfig);
		// set context root and current URI
		rootTarget = restClient.target(rootContextUrl);
		currentTarget = rootTarget;
		restURL=rootContextUrl;
	}

	/**
	 * Set the media type - (default is JSON).
	 */
	public void setMediaType(MediaType type) {
		mediaType = type;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setResourceUrl(String relativeUrl, String baseUrl) {
		if(baseUrl!=null && 
		   !baseUrl.equals("")) {
			restURL = baseUrl;
			restURL += relativeUrl;
			//baseUrl=baseUrl+relativeUrl;
			rootTarget = restClient.target(restURL);
		}
		currentTarget = rootTarget;
	}

	public void addParameter(ParameterType type, String... params){
		switch (type) {
		case FORM:
			if(isKeyValue(params))
				addFormParameter(params[0], params[1]);
			else
				ConsoleLogger.error("The parameter type Form accepts 2 arguments as an input - a key and a value");
			break;
		case QUERY:
			if(isKeyValue(params))
				addQueryParameter(params[0], params[1]);
			else
				ConsoleLogger.error("The parameter type Query accepts 2 arguments as an input - a key and a value");
			break;
		case PATH:
			addPathParameter(params);
		default:
			break;
		}
	}

	public String send(HttpMethod method) {
		try {
			switch (method) {
			case GET:
				sendGet();
				break;
			case POST:
				sendPost();
				break;
			case PUT:
				sendPut();
				break;
			case DELETE:
				sendDelete();
			default:
				break;
			} 
		} finally {
			afterResponse();
			System.out.println("RESPONSE:");
			System.out.println(responseString);
		}
		return responseString;
	}

	public String getResponseBodyAsString() {
		return responseString;
	}

	public Response getResponse() {
		return response;
	}

	public int getStatusCode() {
		return response.getStatus();
	}

	public String getReasonPhrase() {
		return response.getStatusInfo().getReasonPhrase();
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String payload) {
		requestPayload=payload!=null?payload:"";
	}

	private void afterResponse() {
			responseString = response.readEntity(String.class);
			form = new Form();
	}

	public String getRestAuth() {
		return restAuth;
	}

	public void setRestAuth(String restAuth) {
		this.restAuth = restAuth;
	}

	/************** private methods ***************/ 

	private boolean isKeyValue(String... params){
		if(params.length != 2)
			return false;
		return true;
	}

	private void addQueryParameter(String key, String value) {
		currentTarget = currentTarget.queryParam(key, value);
	}

	private void addPathParameter(String... params) {
		for (String param : params) {
			//currentTarget = currentTarget.path(param);
			restURL+=param;
			rootTarget = restClient.target(restURL);
			currentTarget = rootTarget;
		}
	}

	private void addFormParameter(String key, String value) {
		form.param(key, value);
	}

	private void sendGet() {
		response = currentTarget.request(mediaType).header(HttpHeaders.AUTHORIZATION,this.getRestAuth()).get(Response.class);
	}

	private void sendPost() {
		Entity<?> entity = null;
		// check if entity to be sent is form
		if (!form.asMap().isEmpty()) {
			mediaType = MediaType.APPLICATION_FORM_URLENCODED_TYPE;
			entity = Entity.form(form);
		} else
			entity = Entity.entity(requestPayload, mediaType);
		response = currentTarget.request(mediaType).accept(mediaType).post(entity);
	}

	private void sendPut() {
		Entity<?> entity = null;
		// check if entity to be sent is form
		if (!form.asMap().isEmpty()) {
			entity = Entity.form(form);
		}
		else 
			entity = Entity.entity(requestPayload, mediaType);
		response = currentTarget.request(mediaType).accept(mediaType).put(entity);
	}

	private void sendDelete() {
		response=currentTarget.request().delete();
	}

	public String processCall(RestCall call) {

		//set resource relative URL
		this.setResourceUrl(call.getRelativeUrl(),call.getBaseUrl());

		//add path parameters
		for(String path : call.getPathParamsList())
			this.addParameter(ParameterType.PATH,path);

		//add query parameters
		for (Entry<String, String> entry : call.getQueryParamsMap().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			this.addParameter(ParameterType.QUERY, key, value);
		}

		//add form parameters
		for (Entry<String, String> entry : call.getFormParamsMap().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(key.equals("client_secret")) {
				if(!clientSecret.equals("")) {
					value=clientSecret;
				}
				
			}
			this.addParameter(ParameterType.FORM, key, value);
		}

		//add body
		this.setRequestPayload(call.getBody());
		this.setMediaType(call.getMediaType());

		this.setRestAuth(call.getHeaderAuth());
		//finally, send request
		return this.send(call.getMethod());
	}


	// check if string is null or empty
	public boolean empty( String s ) {
		  // Null-safe, short-circuit evaluation.
		  return s == null || s.trim().isEmpty();
		  }
	
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	public String getJenkinsClientSecret() {
		return jenkinsClientSecret;
	}

	public void setJenkinsClientSecret(String jenkinsClientSecret) {
		this.jenkinsClientSecret = jenkinsClientSecret;
	}
	
}