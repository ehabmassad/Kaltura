package com.kaltura;

import java.util.HashMap;
import java.util.Map;

public enum KalturaKdtDictionary {
					// xls action keyword , java class, function in java class
	
REGISTER_USER1("register user and check you got relevant id", "KalturaRestTests", "registerUserAndCheckUsername"),
REGISTER_USER2("register Exist user and check response message", "KalturaRestTests", "registerExistUserAndCheckErrorMessage"),

LOGIN_USER1("login with valid user ", "KalturaRestTests", "loginWithValidUser"),
LOGIN_USER2("login with empty username", "KalturaRestTests", "loginWithEmptyUserName"),

UPDATE_USER1("run rest command", "rest", "runRestCommand"),
UPDATE_USER2("run rest command", "rest", "runRestCommand");

private final String nameInExcel;
private final String methodName;
private final String className;
private static final Map<String, KalturaKdtDictionary> map;

static {
	map = new HashMap<String, KalturaKdtDictionary>();
	for (KalturaKdtDictionary value : KalturaKdtDictionary.values()) {
		map.put(value.nameInExcel, value);
	}
}

KalturaKdtDictionary(String nameInExcel, String className, String methodName) {
	this.nameInExcel = nameInExcel;
	this.className = className;
	this.methodName = methodName;
}

/**,
 * @return name of method
 */
public String getName() {
	return this.methodName;
}

/**
 * @return method name in excel file
 */
public String getNameInExcel() {
	return nameInExcel;
}

/**
 * @return method class name
 */
public String getClassName() {
	return className;
}

/**
 * @param nameInExcel method name in excel file
 * @return java method name as string
 */
public static KalturaKdtDictionary findByKey(String nameInExcel) {
	return map.get(nameInExcel);
}

}
