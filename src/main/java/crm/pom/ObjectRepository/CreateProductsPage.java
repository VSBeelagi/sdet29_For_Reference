package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateProductsPage extends WebDriverUtility{
	
		//declaration
	@FindBy(name= "productname")
	private WebElement ProductnameEdt;
	
	@FindBy(name="productcategory")
	private WebElement productcategoryDropDown;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	
	//initialization
	public CreateProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//utilization
	public WebElement getProductsLnk() {
		return ProductnameEdt;
	}

	public WebElement getProductcategoryDropDown() {
		return productcategoryDropDown;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//business library
	public void createNewProduct(WebDriver driver, String prodName, String Software)
	{
		ProductnameEdt.sendKeys(prodName);
		select(productcategoryDropDown, Software);
		SaveBtn.click();
	}

}

