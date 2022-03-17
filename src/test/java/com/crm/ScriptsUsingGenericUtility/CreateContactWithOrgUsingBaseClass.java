package com.crm.ScriptsUsingGenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.ContactsInfoPage;
import crm.pom.ObjectRepository.ContactsPage;
import crm.pom.ObjectRepository.CreateContactsPage;
import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;

public class CreateContactWithOrgUsingBaseClass extends BaseClass {
	
	@Test
	public void createContactWithOrgTest() throws Throwable
	{
		
		String lastName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		String orgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		String leadsource = eLib.readDataFromExcel("Contacts", 4, 5);
					
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
				
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		//step7: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createNewOrg(orgName);
		
		//step8: verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actorgName = oip.OrgNameInfo();
		if(actorgName.contains(orgName))
		{
			System.out.println(actorgName+"---> data verified");
		}
		else
		{ 
		System.out.println("Org Not Created");
		}
		
		///step4: Navigate to Contacts link
		hp.ClickOnContactLnk();
				
		//step5: click on create Contacts link
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
				
		//Enter Name
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ccp.createNewContact(lastName, orgName);
		
		
		
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String ContHeader = cip.ContactsNameInfo();
		if(ContHeader.contains(lastName))
		{
			System.out.println(ContHeader+" contact created");
		}
		else{
			System.out.println("contact not created");
		}
		
		
		
	}
}


