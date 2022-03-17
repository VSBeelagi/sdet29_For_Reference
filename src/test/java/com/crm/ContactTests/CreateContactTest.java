package com.crm.ContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class CreateContactTest {
	@Test
	public void createContactTest() throws Throwable
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
				
				//step6: create contact with mandatory details and save
				FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestData1.xlsx");
				Workbook wb = WorkbookFactory.create(fi);
				Sheet sh = wb.getSheet("Contcts");
				Row ro = sh.getRow(1);
				Cell cel = ro.getCell(2);
				String LastName = cel.getStringCellValue();
				String LastNameRan = LastName+ random ;
				
				//step2: launch the browser
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
				
				//Navigate to contacts link
				driver.findElement(By.linkText("Contacts")).click();
				
				//step5: click on create Contacts link
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
								
				
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LastNameRan);
				
				//Save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
										
				
				

				
				
				
	}



}
