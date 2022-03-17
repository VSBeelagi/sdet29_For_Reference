package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice2 
{
	@Test(dataProvider="getData")
	public void SampDataProvider2(String Course, int Count)
	{
		System.out.println(Course+"--"+Count);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj= new Object[6][2];
		obj[0][0]="Manual";
		obj[0][1]=500;
		
		obj[1][0]="SQL";
		obj[1][1]=200;
		
		obj[2][0]="Java";
		obj[2][1]=400;
		
		obj[3][0]="Automation";
		obj[3][1]=300;
		
		obj[4][0]="API";
		obj[4][1]=100;
		
		obj[5][0]="Python";
		obj[5][1]=80;
		
		
		return obj;
		
	}

}
