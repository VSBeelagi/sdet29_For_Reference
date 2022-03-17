package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SelectAnyDateFromCalenderTest {
	
	@Test
	public void calender()
	{
		int date = 15;
		String monthAndYear = "May 2022";
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.makemytrip.com/");
		
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		String arrowXpath = "//span[@aria-label='Next Month']";
		String datexpath = "//div[text()='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']";
		
		for(;;) {
			try{
				driver.findElement(By.xpath(datexpath)).click();
				break;
			}
			catch(Exception e){
				driver.findElement(By.xpath(arrowXpath)).click();
			}
		}
		
		
	}

}
