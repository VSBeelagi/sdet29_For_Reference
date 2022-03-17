package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class LeadsPage extends WebDriverUtility {
	
	//declaration
	@FindBy(xpath= "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createLeadsLookUpImg;
	
	
	//initialization
	public LeadsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//utilization
	public WebElement getCreateLeadsLookUpImg() {
		return createLeadsLookUpImg;
	}
	
	//business libraries
	public void clickOncreateLeadsLookUpImg()
	{
		createLeadsLookUpImg.click();
	}
	

}
