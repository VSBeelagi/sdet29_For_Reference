package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


/**
 * This class will read data from property File and return value to user
 * @author Vijayalaxmi
 * 
 */
public class PropertyFileUtility {
	
	/**
	 * @throws Throwable 
	 * this method will read data from property file for the key given by user and return
	 * @param key
	 * @return
	 * @throws
	 */
	public String readDataFromPropertyFile(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.FilePath);
		Properties pLib = new Properties();
		pLib.load(fis);
		String value = pLib.getProperty(key);
		return value;
		
	}
	
	

}
