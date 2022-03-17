package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class DataVerificationINDB {
	@ Test
	public void dataVerificationInDb() throws Throwable
	{
	String expData = "Neha";
	
	//step1: register the database
	Driver driverRef = new Driver();
	DriverManager.registerDriver(driverRef);
	
	//step2: get connector from register
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "root");
	
	//step3: issue create statement
	Statement state = con.createStatement();
	
	//step4: execute Query
	ResultSet result = state.executeQuery("select * from student;");
	
	while(result.next()){
		 String actData = result.getString(2);
		 if(expData.equalsIgnoreCase(actData))
		 {
			 System.out.println("data is verified");
			 break;
		 }
	}
	
	con.close();
	
	
	

}
}