package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	//step1: Declaration
	@FindBy(linkText = "Organizations")
	private WebElement oraganizationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;

	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLnk;

	
	@FindBy(linkText = "Products")
	private WebElement productsLnk;

	@FindBy(linkText = "More")
	private WebElement moreLnk;
	
	@FindBy(linkText = "Campaign")
	private WebElement campaignsLnk;
	
	@FindBy(linkText = "Leads")
	private WebElement LeadsLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;

	//Step 2: initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//step3: generate Getters
	public WebElement getOrganizationLnk() {
		return oraganizationLnk;	
	}
	
	public WebElement getContactsLnk() {
		return contactsLnk;
	}
	
	public WebElement getOpportunities() {
		return opportunitiesLnk;
	}
	
	public WebElement getProductLnk() {
		return productsLnk;
	}
	
	public WebElement getCampaignsLnk() {
		return campaignsLnk;
	}
	
	public WebElement getAdmiministratorImg() {
		return administratorImg;
	}
	
	public WebElement getSignoutLnk() {
		return signOutLnk;
	}
	public WebElement getLeadsLnk() {
		return LeadsLnk;
	}

	//Business Library
	public void ClickOnOrgLnk()
	{
		oraganizationLnk.click();
	}
	
	public void ClickOnContactLnk()
	{
		contactsLnk.click();
	}
	
	public void ClickOnProductLnk()
	{
		productsLnk.click();
	}
	
	public void ClickOnCampaignLnk()
	{
		campaignsLnk.click();
	}
	
	public void ClickOnLeadsLnk()
	{
		LeadsLnk.click();
	}
	
	public void signOutOfApp(WebDriver driver){
		{
			mousehover(driver, administratorImg);
			signOutLnk.click();
		}
	}

}	



























