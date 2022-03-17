package com.crm.ScriptsUsingGenericUtility;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

public class KeepAllEditableMandatoryFieldsBlankTC_34 {
	@Test
	public void keepAllEditableMandatoryFieldsBlank() throws Throwable
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
		
		//step4: Navigate to Leads
		driver.findElement(By.linkText("Leads")).click();
		
		//Step5: create a new Leads
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		//step6: enter mandatory fields and save
		driver.findElement(By.name("lastname")).sendKeys(LeadName);
		driver.findElement(By.name("company")).sendKeys(CompName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step7: Navigate to Leads
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//a[@title='Leads']")).click();
		
		//step8:Navigate Actions and click on convert Lead		
		driver.findElement(By.linkText("Convert Lead")).click();
		
		//step9: Window popup
		wLib.switchToWindow(driver, "Convert");
		
		//clear mandatory fields
		driver.findElement(By.className("detailedViewTextBox")).clear();
			
				driver.findElement(By.name("Save")).click();
			
				wLib.switchToWindow(driver, "parenttab");
	}

}
