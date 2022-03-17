package crm.pom.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	//step1: Declaration
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createProductsLookUpImg;
	
	//step2: initialization
	public ProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	// step3: Utilization
	public WebElement getProductsLnk() {
		return createProductsLookUpImg;
	}
	
	//Business Library
	public void clickOnCreateProductImg()
	{
		createProductsLookUpImg.click();
	}

}
