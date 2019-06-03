package kaltura.kaltura.restapi.test.base;


import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;


import com.google.common.collect.Multimap;

import kaltura.kaltura.restapi.PropInits;
import kaltura.kaltura.restapi.RestCall;
import kaltura.kaltura.restapi.RestObject;
import kaltura.kaltura.restapi.RestResponse;
import kaltura.kaltura.restapi.client.RestClient;
import kaltura.kaltura.restapi.test.utils.RestParser;
import kaltura.kaltura.restapi.test.utils.RestValidator;
import kaltura.kaltura.restapi.test.utils.XlsReader;

/**
 * Base Test Class for Rest API Tests.
 * 
 *  Author Ehab Massad
 */
public class BaseRestAPI {

	public RestClient restClient;
	public static Hashtable<String, Object> data=new Hashtable<String, Object>();
	private RestValidator restValidator;
		// The required run mode to run 
	private static int testRunMode;
	
	/**
	 * Maps flows to invocation methods note: the invocation methods are
	 * represented as a string - using reflection API, the relevant method will
	 * be invoked.
	 */
	private Multimap<String, RestObject> keywordsMap;



	public void init() {
		
		restClient = new RestClient(PropInits.getInstance().getRootRestUrl());
		//PropInits.getInstance().getRootRestUrl())
		restValidator = new RestValidator(restClient);
		
		// Take the only the first run char of the run mode string and convert it to int
	
		
	}
	
	/*	@SuppressWarnings("unchecked")
	private void initializeKeywords() {
		Object[][] restData = getRestData("Details", xls, "RestData");
		// TODO: validate rest Data is not empty

		keywordsMap = ArrayListMultimap.create();
		
		String currentAction = "";

		for (int i = 0; i < restData.length; i++) {

			Hashtable<String, String> rowData = (Hashtable<String, String>) restData[i][0];
			String action = rowData.get("Action");

			if (action.equals("EOFLOW"))
				continue;
			else if (action.equals("N/A") || action.isEmpty()) {
				keywordsMap.put(currentAction, createRestObject(rowData));
			} else {
				currentAction = action;
				keywordsMap.put(currentAction, createRestObject(rowData));
			}
		}
	}
*/
	private RestObject createRestObject(Hashtable<String, String> rowData) {
		
		RestCall call = RestParser.parseCall(rowData);
		RestResponse response = RestParser.parseResponse(rowData);
		return new RestObject(call, response);
	}

	//Get first row for the area. area is the label of the merged cells above the headers ("Details")
	private static int getFirstRow(XlsReader xls, String sheet, String area){

		int testStartRowNum = 0;

		// find the row num from which test starts
		for (int rNum = 1; rNum <= xls.getRowCount(sheet); rNum++) {
			if (xls.getCellData(sheet, 0, rNum).equals(area)) {
				testStartRowNum = rNum;
				break;
			}
		}
		
		return testStartRowNum;
	}

	// Get columns num from which the test starts 
	private static int getTestCols(XlsReader xls, String sheet, int testStartRowNum) {

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;

		// Count headers till find empty header column
		while (!xls.getCellData(sheet, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}
		
		return totalCols;
	}
	
	// Get the number of rows for the test
	private static int getTestRows(XlsReader xls,  String sheet, int testStartRowNum) {
		int dataStartRowNum = testStartRowNum + 2;
		int totalRows = 0;
		while (!xls.getCellData(sheet, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}

		return totalRows;
	}
	
	// 
	public static Object[][] getRestData(String area, XlsReader xls, String sheet) {
		int testStartRowNum = 0;
		// find the row num from which test starts
		for (int rNum = 1; rNum <= xls.getRowCount(sheet); rNum++) {
			if (xls.getCellData(sheet, 0, rNum).equals(area)) {
				testStartRowNum = rNum;
				break;
			}
		}
		System.out.println("Test " + area + " starts from " + testStartRowNum);

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;
		while (!xls.getCellData(sheet, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}
		System.out.println("Total Cols in test " + area + " are " + totalCols);

		// rows
		int dataStartRowNum = testStartRowNum + 2;
		int totalRows = 0;
		while (!xls.getCellData(sheet, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}
		System.out.println("Total Rows in test " + area + " are " + totalRows);

		// extract data
		Object[][] data = new Object[totalRows][1];
		int index = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				table.put(xls.getCellData(sheet, cNum, colStartRowNum), xls.getCellData(sheet, cNum, rNum));
				System.out.print(xls.getCellData(sheet, cNum, rNum) + " -- ");
			}
			data[index][0] = table;
			index++;
			System.out.println();
		}
		System.out.println("done");

		return data;
	}
	
	public String getRootRestUrl(){
		return PropInits.getInstance().getRootRestUrl(); 
	}
	
	public Multimap<String, RestObject> getKeywordsMap(){
		return this.keywordsMap; 
	}

	public void validate(RestResponse restResponse) throws JsonProcessingException, IOException {
		restValidator.validate(restResponse);
		
	}

	public String  processCall(RestCall restCall) {
		return restClient.processCall(restCall);
	}
}
