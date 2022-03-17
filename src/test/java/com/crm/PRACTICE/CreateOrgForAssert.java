package com.crm.PRACTICE;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;

public class CreateOrgForAssert extends BaseClass
{
	
	@Test(groups={"SmokeSuite","RegressionSuite"})

	public void createOrgTest() throws Throwable
	{
		SoftAssert sa = new SoftAssert();
		
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		
		
		
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
		String ExpData = "Organizations";
		String actData = driver.findElement(By.linkText("Organizations")).getText();
		Assert.assertEquals(actData, ExpData);
		
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		String expHeader = "Creating new Organization";
		String actHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		sa.assertEquals(actHeader, expHeader);
		
		
		//step7: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		//step8: verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		Reporter.log(actOrgName+"Org Created", true);
		
		Assert.assertTrue(actOrgName.contains("abc"));
		System.out.println("pass");
		sa.assertAll("all ok");
		
		
	}
	}