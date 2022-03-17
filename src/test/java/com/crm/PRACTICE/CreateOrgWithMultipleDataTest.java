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

import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;

public class CreateOrgWithMultipleDataTest {
	
	
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtility eLib = new ExcelFileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	@Test(dataProvider = "OrgtestData")
	public void createOrgWithMultipleDataTest(String OrgName, String indType, String AccType) throws Throwable
	{
				
		/*Step1: read all necessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String orgName = OrgName+jLib.getRandomNumber();
		
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
		
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
		Reporter.log("navigated to org link", true);
		
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		Reporter.log("click on create org link", true);
		
		
		//step7: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createNewOrg(orgName);
		cop.createNewOrg(indType, AccType);
		Reporter.log("create org with industry type", true);
		
		//step8: verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		if(actOrgName.contains(orgName))
		{
			System.out.println("passed");
		}
		else
		{ 
			System.out.println("failed");
		}
		Reporter.log("Verification successful", true);
		
		
		//logout
			hp.signOutOfApp(driver);
			
			//close the browser
				driver.quit();
	}
	
	@DataProvider(name = "OrgtestData")
	public Object[][] getData() throws Throwable
	{
		Object[][] data= eLib.readmultipleDataFromExcel("OrgMultipleData");
		return data;
	}

}

	

	