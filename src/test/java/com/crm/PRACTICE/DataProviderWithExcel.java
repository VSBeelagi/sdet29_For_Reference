package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;

public class DataProviderWithExcel {
	@Test(dataProvider = "data")
	public void data(String orgname, String indType, String AccType)
	{
		System.out.println(orgname +"  "+indType+"  "+AccType);
	}
	
	@DataProvider(name= "data")
	public Object[][] getData() throws Throwable
	{
		ExcelFileUtility eLib = new ExcelFileUtility();
		Object[][] value = eLib.readmultipleDataFromExcel("OrgMultipleData");
		return value;
	}

}
