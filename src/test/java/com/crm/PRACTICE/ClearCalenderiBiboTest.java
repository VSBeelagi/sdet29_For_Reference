package com.crm.PRACTICE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ClearCalenderiBiboTest {
	@Test
	
	public void clearCalenderiBiboTest()
	{

		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com");
		
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		String arrowXpath = "//span[@aria-label='Next Month']";
	String datexpath ="//div[@aria-label='Wed Aug 03 2022']";
	
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
