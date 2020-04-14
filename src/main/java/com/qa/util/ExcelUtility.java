package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelUtility {
	public static Workbook  book;
	public static Sheet sheet;
	public static String TEST_DATA_PATH="D:\\Users\\asingh6766\\eclipse-workspace\\RestAssuredAPIFramework\\src\\main\\java\\com\\qa\\testdata\\GoRestUserData.xlsx";
	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream fis =null;
		try {
			fis=new FileInputStream(TEST_DATA_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	int totalRows=sheet.getLastRowNum();
	int column = sheet.getRow(0).getLastCellNum();
	
	Object data[][]=new Object[totalRows][column];
	
	for(int i=0;i<totalRows;i++)
	{
		for(int k=0;k<column;k++)
		{
			data[i][k]=sheet.getRow(i+1).getCell(k).toString();
		}
	}
	return data;
	
		
	}
		
	@DataProvider
	public static Object[][] getUserData()
	{
		Object userData[][] = ExcelUtility.getTestData("createuser");
		return userData;
	}
	
	@Test(dataProvider="getUserData")
	public void createUser(String firstname,String lastname, String gender, String dob, String email,String phoneNumber, String website, String address, String status)
	{
		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(gender);
		System.out.println(email);
		System.out.println(dob);
		System.out.println(website);
		System.out.println(address);
		System.out.println(status);
		
	
	}

}
