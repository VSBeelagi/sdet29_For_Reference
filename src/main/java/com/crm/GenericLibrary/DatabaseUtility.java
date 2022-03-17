package com.crm.GenericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	
	Connection con = null;
	/**
	 * This class contains generic methods to read data from database
	 * @author Vijayalaxmi
	 * @throws Throwable 
	 */

	public void connectToDB() throws Throwable
	{
		Driver driver =new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(IPathConstants.dbURL, IPathConstants.dbUsername, IPathConstants.dbPassword);
		
	}
	
	/**
	 * This method will close database connection
	 * @throws Throwable
	 */
	public void closeDB() throws Throwable
	{
		con.close();
	}
	
	/**
	 * This method will execute the query and return the matching data to the user
	 * @param query
	 * @param columnIndex
	 * @param expData
	 * @return
	 * @throws Throwable
	 */
	public String executeQueryAndGetData(String query, int columnIndex, String expData) throws Throwable
	{
		String data= null;
		boolean flag=false;
		ResultSet result = con.createStatement().executeQuery(query);
		while (result.next())
		{
			data= result.getString(columnIndex);
			if(data.equalsIgnoreCase(expData))
			{
				flag= true;  //flag rising
				break;
			}
		}
			
		if(flag)
		{
			System.out.println(data+"---> data verified");
			return expData;
		}
		else
		{
			System.out.println("data not verified");
			return "";
		}
		
	}
	
}
