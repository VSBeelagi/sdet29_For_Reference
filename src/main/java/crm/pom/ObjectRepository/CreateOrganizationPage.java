package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility{
	
	//step1: Declaration
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement Savebtn;
	
	//step2: initialization
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//step3: Utilization
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}
	
	public WebElement getIndusttryDropDown() {
		return industryDropDown;
	}
	
	public  WebElement getTypeDropDown() {
		return typeDropDown;
	}
	
	public  WebElement getSaveBtn() {
		return Savebtn;
	}
	
	//Business LIbrary
	public void createNewOrg(String orgName)
	{
		OrgNameEdt.sendKeys(orgName);
		Savebtn.click();
	}
	
	public void createNewOrg(String orgName, String indType)
	{
		OrgNameEdt.sendKeys(orgName);
		select(indType, industryDropDown);
		Savebtn.click();
	}
	
	public void createNewOrg(String orgName, String indType, String AccType)
	{
		OrgNameEdt.sendKeys(orgName);
		select(indType, industryDropDown);
		select(AccType, typeDropDown);
		Savebtn.click();
		}
	
	
}



















