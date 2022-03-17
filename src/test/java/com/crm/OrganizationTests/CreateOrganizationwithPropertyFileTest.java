package com.crm.OrganizationTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateOrganizationwithPropertyFileTest {
@Test
	
	public void createOrgTest() throws Throwable
	{
		//step1: read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
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
		
		//step4: Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step5: click on create org link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step6: create org with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("ALL india");
		
		//step7: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.quit();
		//driver.quit();
	}
	


}
