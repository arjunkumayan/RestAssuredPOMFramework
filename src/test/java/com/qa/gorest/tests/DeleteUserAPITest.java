package com.qa.gorest.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;
import com.qa.listeners.AllureReportListener;
import com.qa.pojo.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.Assert;

@Epic("Epic - delete USER")
@Feature("US-101: define the delete user feature")
@Listeners(AllureReportListener.class)
public class DeleteUserAPITest {
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "g0JIGvfO6SIYnwmMYDM-Kp3nCYVw-xInSDif";
	
	
	@Description("test case name: delete user api with a new user...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void deleteUserTest()
	{
		
		System.out.println("========= Creating A New User==================");

		// Create a new user : using POST

		Users user = new Users();
		user.setFirst_name("Priya");
		user.setLast_name("J");
		user.setGender("male");
		user.setDob("15-08-1989");
		user.setEmail("priya1234@gmail.com");
		user.setPhone("+1-345-294-0338");
		user.setWebsite("https://www.vbcx.com");
		user.setAddress("test address");
		user.setStatus("active");

		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		String id = response.jsonPath().get("result.id");

		System.out.println("new user id: " + id);
		System.out.println("========");
		
		Response deleteResponse = RestClient.doDelete("JSON", baseURI, basePath+"/"+id, token, true);
		System.out.println(deleteResponse.prettyPrint());
		
		int responseCode=deleteResponse.jsonPath().get("_meta.code");
		System.out.println("delete user api response status code: "+responseCode);
		Assert.assertEquals(responseCode, Constants.HTTP_STATUS_CODE_204);
		Assert.assertNull(deleteResponse.jsonPath().get("result"));
		
		/*
		 * int limit = deleteResponse.jsonPath().get("_meta.rateLimit.limit"); int
		 * remaining = deleteResponse.jsonPath().get("_meta.rateLimit.remaining"); int
		 * reset = deleteResponse.jsonPath().get("_meta.rateLimit.reset");
		 * 
		 * Assert.assertEquals(remaining, limit-reset);
		 */
				
	}

}
