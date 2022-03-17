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

@Test
public class CreateOppoWithContactAndCamp {
	

	private static final String Contacts = null;

	public void ceateOppoWithContactAndCamp() throws Throwable
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
		
		String LastName = eLib.readDataFromExcel("Opportunities", 1, 0)+"_"+jLib.getRandomNumber();
		String CampName = eLib.readDataFromExcel("Opportunities", 1, 1)+"_"+jLib.getRandomNumber();
		String OppName = eLib.readDataFromExcel("Opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		
	
				
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
		
		//step4: Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
				
		//step5: click on create Contacts link
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify the Org
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(header.contains(LastName))
		{
			System.out.println("header");
			System.out.println("Contacts created");
		}
		else
		{
			System.out.println("header");
			System.out.println("Contacts not created");
		}
		
		//Mouse Hover action
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		
		wLib.mousehover(driver, element);
		
		driver.findElement(By.name("Campaigns")).click();
		
		//Create Campaign
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("campaignname")).sendKeys(CampName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(CampName))
		{
			System.out.println("header");
			System.out.println("Campaign created");
		}
		else
		{
			System.out.println("header");
			System.out.println("Campaign not created");
		}
		
		//Create Opportunities
		driver.findElement(By.partialLinkText("Opportunities")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("potentialname")).sendKeys(OppName);
		
		//Dropdown
		WebElement ele = driver.findElement(By.id("related_to_type"));
		
		wLib.select("Contacts", ele);
		
		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
		
		wLib.switchToWindow(driver, "Contacts");
		driver.findElement(By.name("search_txt")).sendKeys(LastName);
		driver.findElement(By.xpath("input[@type='button']")).click();
		
		wLib.switchToWindow(driver, "action");
		
		driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
		
		wLib.switchToWindow(driver, "Popup");
		driver.findElement(By.id("search_txt")).sendKeys(CampName);
		driver.findElement(By.xpath("input[@type='button']")).click();
		
		driver.findElement(By.xpath("//a[.='"+CampName+"']")).click();
		
		wLib.switchToWindow(driver, "Accounts");
		
		driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(header.contains(CampName))
		{
			System.out.println("header");
			System.out.println("Org Information created");
		}
		else
		{
			System.out.println("header");
			System.out.println("Org Information not created");
		}
		
	}

}





























