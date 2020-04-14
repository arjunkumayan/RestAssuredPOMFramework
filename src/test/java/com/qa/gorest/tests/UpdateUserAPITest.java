package com.qa.gorest.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.listeners.AllureReportListener;
import com.qa.pojo.Users;
import com.qa.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.Assert;

@Listeners(AllureReportListener.class)
public class UpdateUserAPITest {
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "g0JIGvfO6SIYnwmMYDM-Kp3nCYVw-xInSDif";

	@Test(enabled = false)
	public void createANewUserAPIPUTTest_WithConstructor() {

		System.out.println("========= Creating A New User==================");

		// Create a new user : using POST
		Users user = new Users("Arjun1", "Kumaya", "male", "15-07-1990", "arjunku33@gmail.com", "+1-994-294-0198",
				"https://www.google.com", "address book 1", "active");
		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		String id = response.jsonPath().get("result.id");

		System.out.println("new user id: " + id);
		System.out.println("========");

		// Update the same user : using PUT call

		user = new Users("Arjun1", "Kumaya", "male", "15-07-1990", "arjunku3@gmail.com", "+1-994-294-0198",
				"https://www.google.com", "address book 1", "inactive");

		Response responseUpdate = RestClient.doPut("JSON", baseURI, basePath + "/" + id, token, true, user);

		System.out.println(responseUpdate.prettyPrint());

		// ID validation
		String updatedId = responseUpdate.jsonPath().get("result.id");
		Assert.assertEquals(updatedId, id);

		// Assertion for Status

		String responseStatus = TestUtil.getStringJSONPath(responseUpdate, "result.status");
		Assert.assertEquals(responseStatus, user.getStatus());

		// message validation

		String metaResponseMessage = TestUtil.getStringJSONPath(responseUpdate, "_meta.message");
		// String metaMessage=responseUpdate.jsonPath().get("_meta.message");
		Assert.assertEquals(metaResponseMessage, "OK. Everything worked as expected.");

	}

	@Description("test case name: update user api with a new user with setter...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void createANewUserAPIPUTTest_WithSetter() {

		System.out.println("========= Creating A New User==================");

		// Create a new user : using POST

		Users user = new Users();
		user.setFirst_name("Priyanka");
		user.setLast_name("J");
		user.setGender("male");
		user.setDob("15-08-1989");
		user.setEmail("priyankaq234@gmail.com");
		user.setPhone("+1-994-294-3428");
		user.setWebsite("https://www.vbc.com");
		user.setAddress("test address");
		user.setStatus("active");

		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		String id = response.jsonPath().get("result.id");

		System.out.println("new user id: " + id);
		System.out.println("========");

		// Update the same user : using PUT call

		
		/*
		 * user=new Users("Arjun1", "Kumaya", "male", "15-07-1990",
		 * "arjunku3@gmail.com",
		 * "+1-994-294-0198","https://www.google.com","address book 1","inactive");
		 */
		user.setPhone("+1-194-294-0338");
		  
		  Response responseUpdate=RestClient.doPut("JSON", baseURI, basePath+"/"+id,token, true, user);
		  
		  System.out.println(responseUpdate.prettyPrint());
		  
		  // ID validation 
          String updatedId=responseUpdate.jsonPath().get("result.id");
		  Assert.assertEquals(updatedId, id);
		  
		  // Assertion for Status
		  
		  String responseStatus = TestUtil.getStringJSONPath(responseUpdate,"result.status");
		  Assert.assertEquals(responseStatus, user.getStatus());
		  
		  // message validation
		  
		  String metaResponseMessage = TestUtil.getStringJSONPath(responseUpdate,"_meta.message"); 
		  //String metaMessage=responseUpdate.jsonPath().get("_meta.message");
		  Assert.assertEquals(metaResponseMessage, "OK. Everything worked as expected.");
		  
		  // Phone number validation
		 String responsePhNo = TestUtil.getStringJSONPath(responseUpdate, "result.phone");
		  Assert.assertEquals(responsePhNo, user.getPhone());
		 

	}

}
