package com.kaltura;

/**
 * The Class TestMethod.
 */
public class TestMethodMapper {
	
	/** The class name. */
	private String className;
	
	/** The method name. */
	private String methodName;
	
	/** Test_Type as it shows in the excel */
	private String testType;

	private String actionName;
	
	/**
	 * Instantiates a new test method.
	 *
	 * @param className the class name
	 * @param methodName the method name
	 */
	public TestMethodMapper(String className, String methodName, String testType, String actionName){
		this.className = className;
		this.methodName = methodName;
		this.testType = testType;
		this.actionName = actionName;
	}

	public TestMethodMapper(String className, String methodName, String testType){
		this.className = className;
		this.methodName = methodName;
		this.testType = testType;
	}
	
	public TestMethodMapper(String className, String methodName){
		this.className = className;
		this.methodName = methodName;
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * Sets the class name.
	 *
	 * @param className the new class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * Gets the method name.
	 *
	 * @return the method name showing in the excel
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Sets the method name.
	 *
	 * @param methodName the new method name
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	/** Get the Action Name showing in the excel*/
	public String getActionName() {
		return actionName;
	}

	/** Set the Action Name to replace the Action showing in the excel*/
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
