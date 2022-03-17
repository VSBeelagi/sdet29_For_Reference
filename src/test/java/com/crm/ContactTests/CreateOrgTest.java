	package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateOrgTest {
@Test
public void createOrgTest() throws Throwable
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
			
			//step2: create org with mandatory fields
			FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestData1.xlsx");
			Workbook wb = WorkbookFactory.create(fi);
			Sheet sh = wb.getSheet("Org");
			Row ro = sh.getRow(4);
			Cell cel = ro.getCell(2);
			String OrgName=cel.getStringCellValue();
			String OrgNameRan = OrgName+random;
			
			Cell ce = ro.getCell(3);
			String IndType = ce.getStringCellValue();
			
			//step3: launch the browser
			WebDriver driver = null;
			if(BROWSER.equalsIgnoreCase("chrome"))// runtime polymorphism
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
			
			//step4: Navigate to Organization link
			driver.findElement(By.linkText("Organizations")).click();
			
			//step5: click on create org link
			driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			
			
			//step7: enter mandatory fields
			driver.findElement(By.name("accountname")).sendKeys(OrgNameRan);
			
			//step8: Save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//logout
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			
			Actions Act = new Actions(driver);
			Act.moveToElement(ele).perform();
			
			//Sign Out
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
			
			
	}
}
