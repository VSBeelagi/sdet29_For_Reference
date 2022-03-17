package crm.pom.ObjectRepository;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactsPage extends WebDriverUtility {
	
	//step1: Declaration
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@alt='Select']")
	private WebElement orgNameLookUpImg;
	
	@FindBy(name = "leadsource")
	private WebElement leadsourceDropDown;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	@FindBy(name= "search_text")
	private WebElement searchEdit;
	
	@FindBy(name= "search")
	private WebElement searchBtn;
	
	
	
	//step2: initialization
		public CreateContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}		
		
		public WebElement getLastNameEdt() {
			return lastNameEdt;
		}

		public WebElement getOrgNameLookUpImg() {
			return orgNameLookUpImg;
		}

		public WebElement getLeadsourceDropDown() {
			return leadsourceDropDown;
		}

		public WebElement getSaveBtn() {
			return SaveBtn;
		}

		public WebElement getSearchEdit() {
			return searchEdit;
		}

		public WebElement getSearchBtn() {
			return searchBtn;
		}
		

		//Business Library
		public void createNewContact(String lastName)
		{
			lastNameEdt.sendKeys(lastName);
			SaveBtn.click();
		}
		
		
			public void createNewContact(String lastName, String leadSource)
				{
					lastNameEdt.sendKeys(lastName);
					select(leadSource, leadsourceDropDown);
					SaveBtn.click();
				}
				
		public void createNewContact(WebDriver driver, String lastName, String orgName, String leadsource)
		{
			lastNameEdt.sendKeys(lastName);
			orgNameLookUpImg.click();
			switchToWindow(driver, "Accounts");
			searchEdit.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			switchToWindow(driver, "Contacts");
			select(leadsourceDropDown, leadsource);
			SaveBtn.click();
			
			
		}

}























