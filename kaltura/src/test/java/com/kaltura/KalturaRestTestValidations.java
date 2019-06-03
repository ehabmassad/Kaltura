package com.kaltura;

import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kaltura.kaltura.restapi.test.entities.ErrorMessage;
import kaltura.kaltura.restapi.test.entities.LoginResult;
import kaltura.kaltura.restapi.test.entities.Registration;

public class KalturaRestTestValidations {
	String response;
	Registration register;
	ErrorMessage errorMsg;
	LoginResult login;


	/**
	 * KDT test that Invoked in accordance with xls action column
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void registerUserAndCheckUsername() throws JsonParseException, JsonMappingException, IOException {
		registerMapper();
		String userId=register.getResult().getId();
		Assert.assertTrue(userId!=null&&!userId.equals(""),"check that user name saved successfully with valid id");
		
	}
	
	public void registerExistUserAndCheckErrorMessage() throws JsonParseException, JsonMappingException, IOException {
		errorMapper();
		String errMSg=errorMsg.getResult().getError().getMessage();
		Assert.assertTrue(errMSg.equals("User exists"),"can't register user with empty input");	
	}
	
	
	public void loginWithValidUser() throws JsonParseException, JsonMappingException, IOException {
		loginMapper();
		String userId=login.LoginResultInside().getUser().getId();
		Assert.assertTrue(userId!=null&&!userId.equals(""),"check that login success with valid user");
	}
	
	public void loginWithEmptyUserName() throws JsonParseException, JsonMappingException, IOException {
		errorMapper();
		String errMSg=errorMsg.getResult().getError().getMessage();
		Assert.assertTrue(errMSg.equals("Argument [username or password] cannot be empty"),"can't register user with empty username value");	
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	private void registerMapper() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		register = objectMapper.readValue(response, Registration.class);
		
	}
	
	private void errorMapper() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		errorMsg = objectMapper.readValue(response, ErrorMessage.class);
		
	}

	private void loginMapper() throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper objectMapper = new ObjectMapper();
		login = objectMapper.readValue(response, LoginResult.class);

	}
	
	public void setExpectedBody(String string) {
		// TODO Auto-generated method stub
		
	}
}
