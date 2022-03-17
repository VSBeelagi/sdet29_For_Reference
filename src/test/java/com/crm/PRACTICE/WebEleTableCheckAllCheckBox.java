package com.crm.PRACTICE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.HomePage;

public class WebEleTableCheckAllCheckBox extends BaseClass
{

	@Test(enabled=false)
	public void webEleTableCheckAllCheckBox() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();

		List<WebElement> eles = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
		for(WebElement ele : eles)
		{
			ele.click();
			Thread.sleep(500);
		}
		
	}
	

	/*@Test
	public void webEleTableCheckl̥astCheckBox() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();

		List<WebElement> eles = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
		ArrayList<WebElement> arrele = new ArrayList<WebElement>(eles);
		int size=arrele.size()-1;
		
		arrele.get(size).click();
		
	}*/
	
	/*@Test
	public void printLastName() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();

		List<WebElement> eles = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
		for(WebElement ele : eles)
		{
			String lastName = ele.getText();
			System.out.println(lastName);
		}
	}*/
	
	@Test
	public void webEleTableCheckl̥astCheckBox() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk();
			
		driver.findElement(By.xpath("(//input)[@type='checkbox'][6]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[7]/td[10]/a[.='del']")).click();
		Thread.sleep(2000);
		System.out.println("deleted the contact");
		wLib.acceptAlert(driver);
}
}