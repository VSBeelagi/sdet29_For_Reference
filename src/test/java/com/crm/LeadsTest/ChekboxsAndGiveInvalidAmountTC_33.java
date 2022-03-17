package com.crm.LeadsTest;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ChekboxsAndGiveInvalidAmountTC_33 {
	@Test
	public void chekboxsAndInvalidAmount() throws Throwable
	{
		//generate random number
		Random Ran = new Random();
		int Random = Ran.nextInt(500);
		
		//read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//read data from excel
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Leads");
		Row ro = sh.getRow(1);
		Cell cel = ro.getCell(0);
		String LeadName = cel.getStringCellValue();
		String LeadNameRan = LeadName+Random;
		
		Cell ce = ro.getCell(1);
		String CompanyName = ce.getStringCellValue();
		String CompanyNameRan = CompanyName+Random;
		
			
		Cell amt = ro.getCell(4);
		String InvAmt = amt.getStringCellValue();
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(URL);
		
		//step3: login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step4: Navigate to Leads
		driver.findElement(By.linkText("Leads")).click();
		
		//Step5: create a new Leads
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		//step6: enter mandatory fields and save
		driver.findElement(By.name("lastname")).sendKeys(LeadNameRan);
		driver.findElement(By.name("company")).sendKeys(CompanyNameRan);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step7: Navigate to Leads
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//a[@title='Leads']")).click();
		
		//step8:Navigate Actions and click on convert Lead		
		driver.findElement(By.linkText("Convert Lead")).click();
		
		//step9: Window popup
		Set<String> win = driver.getWindowHandles();
		for(String windId:win)
		{
			driver.switchTo().window(windId);
		}
		
		driver.findElement(By.id("select_potential")).click(); //checkbox
		
		driver.findElement(By.name("amount")).sendKeys(InvAmt);
		
		
	}

}
