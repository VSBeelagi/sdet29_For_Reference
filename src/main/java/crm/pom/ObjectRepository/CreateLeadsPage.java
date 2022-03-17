package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateLeadsPage extends WebDriverUtility{
	
	//declaration
	@FindBy(name = "lastname")
	private WebElement lastnameEdt;
	
	@FindBy(name = "company")
	private WebElement companyEdt;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	//initialization
		public CreateLeadsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//utilization
		public WebElement getLastnameEdt() {
			return lastnameEdt;
		}

		public WebElement getCompanyEdt() {
			return companyEdt;
		}

		public WebElement getSavebtn() {
			return savebtn;
		}

	//business library
		public void createNewLeads(String lastname, String company)
		{
			lastnameEdt.sendKeys(lastname);
			companyEdt.sendKeys(company);
			savebtn.click();
			
		}
	
	}
