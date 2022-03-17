package com.crm.PRACTICE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.Test;

public class PropertyFilePractice {
	@Test
	public void propertyFilePractice() throws Throwable
	{
		//step1: read the file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//step2: create obj of properties
		Properties pObj = new Properties();
		pObj.load(fis);
		
		//step3: read the data
		String url = pObj.getProperty("username");
		
		//Verification
		System.out.println(url);
		
		
	}
	
	}
