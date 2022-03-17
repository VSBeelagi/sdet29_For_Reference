package com.crm.ScriptsUsingGenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;

public class CreatePOMOrgWithIndCatDDusingBaseClass extends BaseClass {

	@Test(groups="RegressionSuite")
	public void createPOMOrgWithIndCatDDusingBaseClass() throws Throwable
	{
		
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		String indType = eLib.readDataFromExcel("Contacts", 4, 3);
		String AccType = eLib.readDataFromExcel("Contacts", 4, 4);
		
		
				
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
		
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		//step6: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName, indType, AccType);
			
					
		//step7: Verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		Assert.assertTrue(actOrgName.contains(OrgName));
		Reporter.log(actOrgName+"Org with Ind Created",true);
		/*if(actOrgName.contains(OrgName))
		{
			System.out.println(actOrgName+"---> data verified");
		}
		else
		{ 
			System.out.println("data invalid");
		}*/
		
					
	}
	}