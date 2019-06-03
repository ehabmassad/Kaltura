package kaltura.kaltura.restapi.test.base;

import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.ITestResult;

public interface IDataDrivenTest extends ITest {

	public String getTestName();

	public void setTestName(String newTestName);

	void renameDDTmethod(ITestResult result, Method testMethod, Object[] parameters);

}
 
