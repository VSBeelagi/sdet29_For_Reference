package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ContactsInfoPage extends WebDriverUtility{
	//step1: declaration
		@FindBy(xpath = "//span[@class= 'dvHeaderText']")
		private WebElement headerText;
		
		//step2: initialization
		public ContactsInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//step3: utilization
		public WebElement getHeaderText() {
			return headerText;
		}
		
		//business library
		public String ContactsNameInfo()
		{
			String ContatsInfo = headerText.getText();
			return ContatsInfo;
		}

	}
