package kaltura.kaltura.restapi.test.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import kaltura.kaltura.restapi.RestCall;
import kaltura.kaltura.restapi.RestResponse;
import kaltura.kaltura.restapi.RestCall.CallData;
import kaltura.kaltura.restapi.RestResponse.ResponseData;
import kaltura.kaltura.restapi.client.RestClient.HttpMethod;



/**
 * This is the parser class which interprets a rest call from an excel row to an
 * actual rest call.
 * 
 * @author Edited By Ehab Massad
 */
public class RestParser {

	static List<String> listParams;

	public static RestCall parseCall(Map<String, String> rowData) {
		RestCall call = new RestCall();

		for (CallData callData : CallData.values()) {
			String data = rowData.get(callData.getName());
			if (data != null)
			// should check if data equals to null
				switch (callData) {
				
				case REQUEST_BODY:
					call.setBody(data);
					break;
					
				case FORM_PARAMETERS:
					call.setFormParamsMap(createParamMap(data));
					break;
	
				case PATH_PARAMETERS:
					call.setPathParamsList(createParamList(data));
					break;
	
				case QUERY_PARAMETERS:
					call.setQueryParamsMap(createParamMap(data));
					break;
	
				case HTTP_METHOD:
					call.setMethod(HttpMethod.valueOf(data.toUpperCase()));
					break;
	
				case RELATIVE_URL:
					call.setRelativeUrl(data);
					break;
					
				case REQUEST_TYPE:
					call.setMediaType(MediaType.valueOf(data));
					break;
				case BASE_URL:
					call.setBaseUrl(data);
					break;
				case HEADER_AUTHEMTICATION:
					call.setHeaderAuth(data);
					break;
				default:
					break;
	
				}

		}
		return call;
	}
	
	public static RestResponse parseResponse(Map<String, String> rowData) {
		RestResponse response = new RestResponse();

		for (ResponseData callData : ResponseData.values()) {
			String data = rowData.get(callData.getName());
			if (data != null)
				switch (callData) {
				case HTTP_RESPONSE:
					response.setResponseCode(parseStatusCode(data));
					response.setReasonPhrase(parseStatusMessage(data));
					break;
				case EXPECTED_BODY:
					response.setExpectedBody(createParamList(data));
					break;
				case EXPECTED_HEADERS:
					response.setExpectedHeaders(createParamMap(data));
					break;
				case REQUEST_SCHEMA:
					break;
				case RESPONSE_SCHEMA:
					break;
				}
	
		}
		return response;
	}

	private static List<String> createParamList(String params) {
		listParams = new ArrayList<String>();
		if(params==null||params.isEmpty()||params.equals("empty"))
			return listParams;
		
		String[] paramsArr = params.split(";");

		for (String param : paramsArr) {
			listParams.add(param);
		}
		return listParams;
	}
	
	private static Map<String, String> createParamMap(String data){
		splitPairsParams(data);
		Map<String,String> paramsMap = new HashMap<String, String>();
		for(int i = 0; i< listParams.size() ; i=i+2 ){
			paramsMap.put(listParams.get(i), listParams.get(i+1));
		}
		return paramsMap;
	}

	private static List<String> splitPairsParams(String params) {
		listParams = new ArrayList<String>();
		if(params==null||params.isEmpty()||params.equals("empty"))
			return listParams;
		
		String[] pairs = params.split(";");

		for (String pair : pairs) {
			String[] parameters = pair.split("=");
			listParams.add(parameters[0]);
			listParams.add(parameters[1]);
		}
		return listParams;
	}
	
	private static int parseStatusCode(String statusLine) {
		String[] statusLineArr = statusLine.split(",");
		return Integer.valueOf(statusLineArr[0]);
	}
	
	private static String parseStatusMessage(String statusLine) {
		String[] statusLineArr = statusLine.split(",");
		return statusLineArr[1];
	}
	
}
