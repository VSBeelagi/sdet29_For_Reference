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

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.CreateLeadsPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LeadsPage;
import crm.pom.ObjectRepository.LoginPage;

public class KeepAllEditableMandatoryFieldsBlankTC_34BaseClass extends BaseClass{
	
	@Test
	public void keepAllEditableMandatoryFieldsBlank() throws Throwable
	{
				
		String LeadName = eLib.readDataFromExcel("Leads", 1, 0)+"_"+jLib.getRandomNumber();
		String CompName = eLib.readDataFromExcel("Leads", 1, 1)+"_"+jLib.getRandomNumber();
					
			
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
		
}
}