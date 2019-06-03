package com.kaltura;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.json.JSONException;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import kaltura.kaltura.restapi.RestCall;
import kaltura.kaltura.restapi.RestObject;
import kaltura.kaltura.restapi.RestResponse;
import kaltura.kaltura.restapi.test.base.BaseRestAPITest;
import kaltura.kaltura.restapi.test.utils.RestParser;
import listeners.DataProviderParams;

public class KalturaMainRestTests extends BaseRestAPITest {
	Hashtable<String, String> rowData;
	String response = "";
	KalturaRestTestValidations kalturaValidations;

	@Test(dataProvider = "ExcelFileLoader", enabled = true)
	@DataProviderParams(sheet = "KalTests", area = "Details")
	public void getTasksInfoWithinSpicificPerioud(Hashtable<String, String> data)
			throws JsonProcessingException, IOException, JSONException, org.codehaus.jettison.json.JSONException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		 rowData = data;
		 rowData.put("BaseURL", getbaseURL());
		// RUN REST CALL UPDATE RESPONSE AND VALIDATE HEADERS
		 runnCall(createRestObject(data));

		// RUN TEST'S RELEVANT VALIDATIONS
		TestMethodMapper testMethod = getTestMethod(rowData.get("Action"));
		runTestByActionKeyWord(testMethod);

	}

	/**
	 * private functions
	 */

	private RestObject createRestObject(Hashtable<String, String> rowData) {
		RestCall call = RestParser.parseCall(rowData);
		RestResponse response = RestParser.parseResponse(rowData);
		return new RestObject(call, response);
	}

	private void runnCall(RestObject restObj)
			throws JsonProcessingException, IOException, JSONException, org.codehaus.jettison.json.JSONException {

		response = baseRestAPI.processCall(restObj.getRestCall());
		baseRestAPI.validate(restObj.getRestResponse());
	}

	private TestMethodMapper getTestMethod(String key) {
		return new TestMethodMapper(KalturaKdtDictionary.findByKey(key).getClassName(),
				KalturaKdtDictionary.findByKey(key).getName());
	}

	private void runTestByActionKeyWord(TestMethodMapper testMethod)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		if (kalturaValidations == null) {
			kalturaValidations = new KalturaRestTestValidations();
		}

		kalturaValidations.setResponse(response);
		kalturaValidations.setExpectedBody(rowData.get("Expected Body"));
		Method[] methods = KalturaRestTestValidations.class.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(testMethod.getMethodName())) {
				method.invoke(kalturaValidations);
			}
		}

	}

}