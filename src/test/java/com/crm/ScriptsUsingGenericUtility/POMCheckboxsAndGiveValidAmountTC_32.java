package com.crm.ScriptsUsingGenericUtility;

import java.util.Set;

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

import crm.pom.ObjectRepository.CreateLeadsPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LeadsPage;
import crm.pom.ObjectRepository.LoginPage;

public class POMCheckboxsAndGiveValidAmountTC_32 {
	@Test
	public void POMcheckboxAndGiveValidAmountTC_32() throws Throwable
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
	
	String LeadName = eLib.readDataFromExcel("Leads", 1, 0)+"_"+jLib.getRandomNumber();
	String CompName = eLib.readDataFromExcel("Leads", 1, 1)+"_"+jLib.getRandomNumber();
	String ValidDate = eLib.readDataFromExcel("Leads", 1, 5).toString();
	String ValAmt = eLib.readDataFromExcel("Leads", 1, 3);
	
	/*Step 2: launch the browser*/
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
	LoginPage lp= new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	
	//step4: Navigate to Leads
	HomePage hp= new HomePage(driver);
	hp.ClickOnLeadsLnk();
	
	//Step5: create a new Leads
	LeadsPage ldp= new LeadsPage(driver);
	ldp.clickOncreateLeadsLookUpImg();
	
	//step6: enter mandatory fields and save
	CreateLeadsPage clp= new CreateLeadsPage(driver);
	clp.createNewLeads(LeadName, CompName);
	
	//step8:Navigate Actions and click on convert Lead		
	driver.findElement(By.linkText("Convert Lead")).click();
	
		//step9: Window popup
		Set<String> win = driver.getWindowHandles();
		for(String windId:win)
		{
			driver.switchTo().window(windId);
		}
		
		driver.findElement(By.id("select_potential")).click(); //checkbox
		
		driver.findElement(By.name("amount")).sendKeys(ValAmt);
		driver.findElement(By.xpath("//img[@src='themes/images/miniCalendar.gif']")).click();
		driver.findElement(By.id("jscal_field_closedate")).sendKeys(ValidDate);
		
		driver.findElement(By.name("Save")).click();
		
		//logout
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				
				wLib.mousehover(driver, element);
				
				//Sign Out
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
}
}
