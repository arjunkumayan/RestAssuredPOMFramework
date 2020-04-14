package com.qa.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TestUtil {
	
	/**
	 * this method is used to convert pojo to json string
	 * @param obj
	 * @return
	 */
	
	public static String getSerilizedJson(Object obj)
	{
		String jsonString=null;
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			//jsonString=	mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			jsonString=	mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			return jsonString;
			}
		
		System.out.println("Payload: "+jsonString);
		return jsonString;
	}
	
	
	public static String getStringJSONPath(Response response, String keyName)
	{
		return response.jsonPath().getString(keyName);
		
	}

	public static String getJSONPath(Response response, String keyName)
	{
		return response.jsonPath().getString(keyName);
		
	}
	
	public static void getRequestSpecificaiton()
	{
		
		RestAssured.given().accept("application/json");
	}

}
