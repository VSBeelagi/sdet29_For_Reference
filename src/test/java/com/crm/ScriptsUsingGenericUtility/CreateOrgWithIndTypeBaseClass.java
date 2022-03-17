package com.crm.ScriptsUsingGenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import crm.pom.ObjectRepository.BaseClass;
import crm.pom.ObjectRepository.CreateOrganizationPage;
import crm.pom.ObjectRepository.HomePage;
import crm.pom.ObjectRepository.LoginPage;
import crm.pom.ObjectRepository.OrganizationInfoPage;
import crm.pom.ObjectRepository.OrganizationPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrgWithIndTypeBaseClass extends BaseClass{

	@Test(groups={"SmokeSuite","RegressionSuite"})
	public void createOrgWithIndTypeBaseClass() throws Throwable
	{
			
		
		String OrgName = eLib.readDataFromExcel("Contacts", 4, 2)+"_"+jLib.getRandomNumber();
		String indType = eLib.readDataFromExcel("Contacts", 4, 3);
		System.out.println(OrgName);
		System.out.println(indType);
		//step4: Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk();
		Assert.fail();
		
		//step5: click on create org link
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgImg();
		
		//step6: enter mandatory fields
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);

		
		//step7: select dropdowns
		cop.createNewOrg(OrgName, indType);
		
					
		//step7: Verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName = oip.OrgNameInfo();
		
		Assert.assertTrue(actOrgName.contains(OrgName));
		Reporter.log(actOrgName+"Org with Ind Created",true);
		/*if(actOrgName.contains(OrgName))
		{
			System.out.println(actOrgName+"---> data verified");
		}
		else
		{ 
			System.out.println("data invalid");
		}*/
		
		
	}
	@Test
		public void createSampleTestCase1()
		{
			System.out.println("Sample Test1");
		}
		
	@Test
		public void createSampleTestCase2()
		{
			System.out.println("Sample Test2");
		}
		
	@Test
		public void createSampleTestCase3()
		{
			System.out.println("Sample Test3");
		}
	}

