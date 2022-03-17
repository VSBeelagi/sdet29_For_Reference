package com.crm.PRACTICE;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertionPractice {
	@Test
	public void asertionPractice()
	{
		
		SoftAssert sa = new SoftAssert();
		System.out.println("This is test 1");
		sa.assertEquals(1, 0);
		System.out.println("passed");
		sa.assertAll();
		Assert.assertEquals(1,0);
		
	
	
	
	
	
	}
	
	

}
