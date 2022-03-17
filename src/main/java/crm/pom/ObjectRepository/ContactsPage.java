package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	//Step1: declaration
		@FindBy(xpath = "//img[@title='Create Contact...']")
		private WebElement createContactsLookUpImg;
		
		//step2: initialization
		public ContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//step3: Utilization
		public WebElement getCreateOrgLookUpImg(){
			return createContactsLookUpImg;
		}
		
		//Business Library
		public void clickOnCreateContactImg()
		{
			createContactsLookUpImg.click();
		}
	}


