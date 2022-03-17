package com.crm.ScriptsUsingGenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrgwithBaseclass extends BaseClass{
	
	@Test(groups={"SmokeSuite","RegressionSuite"})

	public void createOrgTest() throws Throwable
	{
		
		
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		
		
		
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
		
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		
		//step7: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createNewOrg(OrgName);
		
		//step8: verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		if(actOrgName.contains(OrgName))
		{
			System.out.println(actOrgName+"---> data verified");
		}
		else
		{ 
			System.out.println("data invalid");
		}
		
		
	}
	}
		

