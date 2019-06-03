package kaltura.kaltura.restapi.test.utils;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kaltura.kaltura.restapi.ConsoleLogger;
import kaltura.kaltura.restapi.RestResponse;
import kaltura.kaltura.restapi.client.RestClient;



/**
 * This class provides rest validation utilities.
 * 
 * @author Ehab Massad
 */
public class RestValidator {

	private RestClient client;

	public RestValidator(RestClient client) {
		this.client = client;
	}

	public void validate(RestResponse response) throws JsonProcessingException, IOException {

		validateStatusLine(response.getResponseCode(), response.getReasonPhrase());

		validateBody(response.getExpectedBody());

		//validateHeaders(response.getExpectedHeaders());

	}

	private void validateStatusLine(int expectedStatusCode, String expectedReasonPhrase) {
		int actualStatusCode = client.getStatusCode();
		String actualReasonPhrase = client.getReasonPhrase();
		// validate status code
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "validate status code");
		// validate reason phrase
		Assert.assertEquals(actualReasonPhrase, expectedReasonPhrase, "validate reason phrase");
	
	}

	private void validateBody(List<String> expectedBodies) throws JsonProcessingException, IOException {
		MediaType responseMediaType = client.getResponse().getMediaType();
		// iterate over all expected body list
		for (String expectedBody : expectedBodies) {
			// validate JSON response
			if (responseMediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE)) {
				validateJson(expectedBody);
			}
		}
	}

	private void validateHeaders(Map<String, String> expectedHeaders) {

		for (Entry<String, String> entry : expectedHeaders.entrySet()) {
			String expectedKey = entry.getKey();
			entry.getValue();
			String expectedValue = entry.getValue();
			// get actual value of expected key
			client.getResponse().getHeaderString(expectedKey);
			String actualHeaderValue = client.getResponse().getHeaderString(expectedKey);
			Assert.assertEquals(actualHeaderValue, expectedValue, "validate headers content type");
		}
	}

	/**
	 * Assert that a key->value pair exists in JSON string
	 * 
	 * @param json
	 * @param expectedKey
	 * @param expectedValue
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	protected void validateJsonKeyValue(String expectedKey, String expectedValue)
			throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(client.getResponseBodyAsString());
		JsonNode jsonNode = actualObj.get(expectedKey);
		String actualValue = jsonNode.asText();

		Assert.assertEquals(actualValue, expectedValue);

	}

	/**
	 * Assert that two JSON strings are equal
	 * 
	 * @param actualJson
	 * @param expectedJson
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	private void validateJson(String expectedJson) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		ConsoleLogger.info("Expected Json: " + expectedJson);

		JsonNode actualObj = mapper.readTree(client.getResponseBodyAsString());
		ConsoleLogger.info("Actual Json: " + expectedJson);
		JsonNode expectedObj = mapper.readTree(expectedJson);
		Assert.assertEquals(actualObj, expectedObj, "checking body content");

	}

}
