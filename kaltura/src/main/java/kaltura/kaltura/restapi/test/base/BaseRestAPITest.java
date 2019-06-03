package kaltura.kaltura.restapi.test.base;


import java.io.File;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import listeners.DataProviderParams;

import com.google.common.collect.Multimap;

import kaltura.kaltura.restapi.RestObject;
import kaltura.kaltura.restapi.test.utils.XlsReader;

public class BaseRestAPITest implements IDataDrivenTest {

	public BaseRestAPI baseRestAPI;
	protected String rootRestUrl;
	protected Multimap<String, RestObject> keywordsMap;
	public String sheet = "";
	public String area = "";
	private static XlsReader xls;
	public String testName;
	protected Hashtable<String, String> bugReques;
	protected String methodName;
	protected String request;
	protected String actionType;

	public static XlsReader getXlsReader() {
		return xls;
	}

	@BeforeClass
	public void initRestApi() {

		baseRestAPI = new BaseRestAPI();
		baseRestAPI.init();
	}


	@DataProvider(name = "ExcelFileLoader")
	public Object[][] getDataFromXmlFile(final Method testMethod, ITestContext context) {
		DataProviderParams parameters = testMethod.getAnnotation(DataProviderParams.class);
		String excelSheet = parameters.sheet();
				//context.getCurrentXmlTest().getParameter("ExcelSheet");
		if (parameters != null) {
			// String sheet = parameters.sheet();
			String area = parameters.area();
			/*File file = new File(context.getCurrentXmlTest().getParameter("ExcelDataProviderPath"));
			String excelName = file.getName();
			excelName = excelName.split("[.]").length > 1 ? excelName.split("[.]")[0] : excelName;*/

			/*
			 * // add string"DEV" in case we run on the DEV Setup
			 * if(getbaseURL().contains("dev")) { excelName+="DEV"; }
			 */

			xls = new XlsReader(System.getProperty("user.dir") + File.separator + "resources" + File.separator
					+ "kalturaRestTest" + ".xlsx");

			return BaseRestAPI.getRestData(area,xls, excelSheet );
		} else {
			throw new RuntimeException("Couldn't find the annotation");
		}
	}
	
	
	

	
	public String getTestName() {
		return testName;
	}

	
	public void setTestName(String newTestName) {
		testName = newTestName;
	}

	@SuppressWarnings("unchecked")
	@BeforeMethod(alwaysRun = true)
	public void renameDDTmethod(ITestResult result, Method testMethod, Object[] parameters) {
		BaseRestAPI.data.size();
		methodName = testMethod.getName();

		String newMethodName = ((Hashtable<String, String>) parameters[0]).get("Description");

		if (newMethodName != null)
			methodName = newMethodName;
		// methodName="relative url: " + methodName;
		result.setAttribute("ddtTestName", methodName);
		// this.setTestName(methodName);
		result.setAttribute("ddtParams", parameters[0].toString());
		this.setTestName(methodName);

	}

	/*
	 * @Override public String getTestName() { // TODO Auto-generated method stub
	 * return testName; }
	 * 
	 * @Override public void setTestName(String newTestName) { testName =
	 * newTestName; }
	 * 
	 * @Override public void renameDDTmethod(ITestResult result, Method testMethod,
	 * Object[] parameters) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	protected String getbaseURL() {
	
			return baseRestAPI.getRootRestUrl();
		
	}

}