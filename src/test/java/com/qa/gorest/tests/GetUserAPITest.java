package com.qa.gorest.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;
import com.qa.listeners.AllureReportListener;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

@Listeners(AllureReportListener.class)
public class GetUserAPITest {
	
	String baseURI="https://gorest.co.in";
	String basePath="/public-api/users";
	String token="g0JIGvfO6SIYnwmMYDM-Kp3nCYVw-xInSDif";
	
	
	@Description("test case name: verify get user api with a existing user...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getUserListAPITest() {
		Response response=RestClient.doGet("JSON",baseURI, basePath, token, true);
		Assert.assertEquals(RestClient.getStatusCode(response),Constants.HTTP_STATUS_CODE_200);
		RestClient.getPrettyResponsePrint(response);
		
		JsonPath json=RestClient.getJsonPath(response);
		System.out.println(json.getString("_meta.message"));
		
		ArrayList results=json.get("result"); // if you try to add arraylist to string it will give  you classcastexception
		System.out.println(results.size());
		System.out.println(results.get(0));
		
		Map<String,Object> firstUserData =(HashMap<String, Object>) results.get(0);
		
		for(Map.Entry<String, Object> entry:firstUserData.entrySet()){
			System.out.println("Key ="+entry.getKey() + ",Value ="+entry.getValue());
		}
		
	}

}
 