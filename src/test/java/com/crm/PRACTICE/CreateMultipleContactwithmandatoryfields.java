package com.crm.PRACTICE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.ContactsInfoPage;
import crm.pom.ObjectRepository.ContactsPage;
import crm.pom.ObjectRepository.CreateContactsPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;

public class CreateMultipleContactwithmandatoryfields {
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtility eLib = new ExcelFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	@Test(dataProvider = "ContactstestData")
	public void createMultipleContactwithmandatoryfields(String lastName) throws Throwable
	{
				
		/*Step1: read all necessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String LastName = lastName+jLib.getRandomNumber();
		
		
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
		Reporter.log("login successful", true);
		
		//step4: Navigate to Contacts link
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();
		Reporter.log("navigated to org link", true);
		
		//step5: click on create Contacts link
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		Reporter.log("click on create Contacts link", true);	
		
		//Enter Name
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ccp.createNewContact(LastName);
		Reporter.log("Created Contacts", true);
		
		
		//step6: verification
		ContactsInfoPage cip= new ContactsInfoPage(driver);
		String actContName = cip.ContactsNameInfo();
		if(actContName.contains(LastName))
			{
				System.out.println("passed");
			}
		else
			{ 
				System.out.println("failed");
			}
		Reporter.log("Verified Contacts", true);	
		
						
		//logout
		hp.signOutOfApp(driver);
		driver.quit();
	}	
		@DataProvider(name = "ContactstestData")
		public Object[][] getData() throws Throwable
		{
			Object[][] data= eLib.readmultipleDataFromExcel("MultipleContacts");
			return data;
		
		
}
}
