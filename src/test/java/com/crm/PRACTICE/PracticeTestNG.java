package com.crm.PRACTICE;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTestNG {
	@Test(priority= -1)
	public void createOrg()
	{
		System.out.println("org created");
	}

	@Test(dependsOnMethods = "createOrg")
	public void modifyOrg()
	{
		System.out.println("org modified");
		Reporter.log("org created---report+console",true);
	}
	
	@Test(priority= 1)
	public void deleteOrg()
	{
		System.out.println("Org deleted");
	}
}

