package com.crm.ScriptsUsingGenericUtility;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.ContactsInfoPage;
import crm.pom.ObjectRepository.ContactsPage;
import crm.pom.ObjectRepository.CreateContactsPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithBaseClass extends BaseClass {
	
	@Test(retryAnalyzer= com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void createContactTest() throws Throwable
	{
		
		
		String lastName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		
		//step4: Navigate to Contacts link
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();
				
		//step5: click on create Contacts link
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		Assert.fail();
		
		//step:Enter LastName
		CreateContactsPage ccp= new CreateContactsPage(driver);
		ccp.createNewContact(lastName);
		
		//step8: verification
		ContactsInfoPage cip= new ContactsInfoPage(driver);
				String actContName = cip.ContactsNameInfo();
				
				Assert.assertTrue(actContName.contains(lastName));
				Reporter.log(actContName+"Org with Ind Created",true);
				/*if(actContName.contains(lastName))
				{
					System.out.println(actContName+"---> data verified");
				}
				else
				{ 
					System.out.println("data invalid");
				}*/
				
				
	}

}


