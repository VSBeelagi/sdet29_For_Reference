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
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;

public class CreatePOMContactTest {
	@Test
	public void createContactTest() throws Throwable
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
		
		//step4: Navigate to Contacts link
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();
				
		//step5: click on create Contacts link
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//step8: verification
		ContactsInfoPage cip= new ContactsInfoPage(driver);
				String actContName = cip.ContactsNameInfo();
				if(actContName.contains(lastName))
				{
					System.out.println(actContName+"---> data verified");
				}
				else
				{ 
					System.out.println("data invalid");
				}
				
				
				//logout
					hp.signOutOfApp(driver);
					
					//close the browser
						driver.quit();
	}

}
