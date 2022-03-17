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

public class CreateContactWithOrgTest {
	
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
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		
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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step4: Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step5: click on create org link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step7: enter mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		
		//step8: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify the Org
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(header.contains(OrgName))
		{
			System.out.println("header");
			System.out.println("Org created");
		}
		else
		{
			System.out.println("header");
			System.out.println("Org not created");
		}
		
		//step4: Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//step5: click on create Contacts link
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Enter Name
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
		
		//Choose Org
		wLib.switchToWindow(driver, "Acoounts");
		driver.findElement(By.name("search_text")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		
		wLib.switchToWindow(driver, "Contacts");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify for Contact
		String ContHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ContHeader.contains(lastName))
		{
			System.out.println(ContHeader+" contact created");
		}
		else{
			System.out.println("contact not created");
		}
		
		//logout
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		wLib.mousehover(driver, element);
		
		//Sign Out
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
		
		
		
	}

}
