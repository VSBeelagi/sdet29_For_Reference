package com.crm.ContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateHealthCareIndExistingOrgTest{
@Test
public void createHealthCareIndustryTest() throws Throwable
{
	/*generate random number*/
	Random ran = new Random();
	int random = ran.nextInt(500);
	
	
	//step1: read data from property file
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
			Properties pObj = new Properties();
			pObj.load(fis);
			String BROWSER = pObj.getProperty("browser");
			String URL = pObj.getProperty("url");
			String USERNAME = pObj.getProperty("username");
			String PASSWORD = pObj.getProperty("password");
			
			//step2: create contact with mandatory details and save
			FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestData1.xlsx");
			Workbook wb = WorkbookFactory.create(fi);
			Sheet sh = wb.getSheet("Contacts");
			Row ro = sh.getRow(4);
			Cell cel = ro.getCell(2);
			String OrgName=cel.getStringCellValue();
			System.out.println(OrgName);
			
			String OrgNameRan = OrgName+random;
			
			Row r = sh.getRow(1);
			Cell c = r.getCell(2);
			String LastName = c.getStringCellValue();
			String LastNameRan = LastName+random;
			
			//step3: launch the browser
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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.get(URL);
			
			//step3: login
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			//Navigate to Org
			driver.findElement(By.linkText("Organizations")).click();
			
			//Create Org
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			
			//Enter Org name
			driver.findElement(By.name("accountname")).sendKeys(OrgNameRan);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//Verify the Org
			String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(header.contains(OrgNameRan))
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
			driver.findElement(By.name("lastname")).sendKeys(LastNameRan);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
			
			//Choose Org
			Set<String> win = driver.getWindowHandles();
			for(String windId:win)
			{
				driver.switchTo().window(windId);
			}
			driver.findElement(By.name("search_text")).sendKeys(LastNameRan);
			driver.findElement(By.name("search")).click();
			
			driver.findElement(By.xpath("//a[.='"+OrgNameRan+"']")).click();
			
			Set<String> win1 = driver.getWindowHandles();
			for(String wi:win1)
			{
				driver.switchTo().window(wi);
			}
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//Verify for Contact
			String ContHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(ContHeader.contains(LastNameRan))
			{
				System.out.println(ContHeader+" contact created");
			}
			else{
				System.out.println("contact not created");
			}
			
			//logout
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			
			Actions Act = new Actions(driver);
			Act.moveToElement(ele).perform();
			
			//Sign Out
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
			
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
}
