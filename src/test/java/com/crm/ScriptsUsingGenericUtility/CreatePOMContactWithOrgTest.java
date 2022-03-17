package com.crm.ScriptsUsingGenericUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.ContactsInfoPage;
import crm.pom.ObjectRepository.ContactsPage;
import crm.pom.ObjectRepository.CreateContactsPage;
import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;

public class CreatePOMContactWithOrgTest {
	@Test
	public void createContactWithOrgTest() throws Throwable
	{
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtility eLib = new ExcelFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		
		/*Step1: read all necessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String lastName = eLib.readDataFromExcel("Contacts", 1, 2)+"_"+jLib.getRandomNumber();
		String orgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		String leadsource = eLib.readDataFromExcel("Contacts", 4, 5);
		
		
		/*Step2: Launch the browser*/
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		/*Step3: Login to app*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
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
		ccp.createNewContact(driver, lastName, orgName, leadsource);
		
		
		
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String ContHeader = cip.ContactsNameInfo();
		if(ContHeader.contains(lastName))
		{
			System.out.println(ContHeader+" contact created");
		}
		else{
			System.out.println("contact not created");
		}
		
		//logout
		hp.signOutOfApp(driver);
		
		//close the browser
			driver.quit();
		
		
	}
}
