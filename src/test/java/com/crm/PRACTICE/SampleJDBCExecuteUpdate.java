package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteUpdate {
	
	@Test
	
	public void sampleJDBCExecuteUpdate() throws Throwable
	{
		//step1: register the database
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//step2: get connector from register
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "root");
				
		//step3: issue create statement
		Statement stat = con.createStatement();
		
		//step4: execute a query
		//insert into Student values('Neha', 5,'Mysore')
		int result = stat.executeUpdate("insert into student values(5,'Neha', 'Mysore');");
		if(result==1)
		{
			System.out.println("data added successfully");
		}
		else
		{
			System.out.println("data not added");
		}
		
		//step5: close database
		con.close();
		
		
		
		
		
	}

}
