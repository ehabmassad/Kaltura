package kaltura.kaltura.restapi;

import kaltura.kaltura.restapi.test.utils.TestPropsReader;

public class PropInits {

	private static PropInits instance = null;

	private String rootRestUrl;
	private String nameOfXls;
	private String client_secret;
	private String testRunMode;
	
	private PropInits() {
		this.rootRestUrl = TestPropsReader.get("common.base.rest.baseURL");
		this.nameOfXls = TestPropsReader.get("common.rest.excel.name");
		this.client_secret = TestPropsReader.get("common.rest.client_secret");
		this.testRunMode = TestPropsReader.get("common.rest.excel.testRunMode");
	}

	public String getTestRunMode()
	{
		return testRunMode;
	}
	
	public String getRootRestUrl() {
		return rootRestUrl;
	}
	
	public String getNameOfXls() {
		return nameOfXls;
	}
	
	public String getClientSecret() {
		return client_secret;
	}
	
	public static PropInits getInstance() {
		if (instance == null) {
			instance = new PropInits();
		}
		return instance;
	}
}
