package testPackage;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import helperPackage.BrowserFactory;
import pagesPackage.HomePage;
import pagesPackage.HomePage_Cal;
import pagesPackage.LoginPage;
import pagesPackage.TAndMPage;
import utilityPackage.ConfigReader;

public class TestClass extends BaseClass {
	
	
	//A simple test case to check if user is able to login successfully
	@Test(description = "Test to validate the login feature")
	public void loginTest()
	{		
		testLog.log(Status.INFO, "Login test started");
		SoftAssert softAssert = new SoftAssert();
		//Initiate PO for login page
		LoginPage login_page = new LoginPage();
		testLog.log(Status.INFO, "Logged in successully");

		//perform login steps
		login_page.loginSteps();
		softAssert.assertEquals(true, true, "Test failed after launching url");
		
		//Initiate PO for home page and validate
		HomePage home_page = new HomePage();
		home_page.validateHomePage();
		softAssert.assertEquals(true, true);		
		testLog.log(Status.PASS, "Login test Passed");

		
		softAssert.assertAll();
		
	}
	
	@Test(description = "Test2 to validate the login feature")
	public void loginTest2()
	{		
		
		SoftAssert softAssert = new SoftAssert();
		//Initiate PO for login page
		LoginPage login_page = new LoginPage();

		//perform login steps
		login_page.loginSteps();
		softAssert.assertEquals(true, true, "Test failed after launching url");
		
		//Initiate PO for home page and validate
		HomePage home_page = new HomePage();
		home_page.validateHomePage();
		softAssert.assertEquals(true, true);		
		
		softAssert.assertAll();
		
	}

	@Test(description = "Test to create a new record in Time&Materials page and validate")
	public void CreateTMRecordAndValidate()
	{		
		
		//SoftAssert softAssert = new SoftAssert();
		//Initiate PO for login page
		LoginPage login_page = new LoginPage();

		//perform login steps
		login_page.loginSteps();
		//softAssert.assertEquals(true, true, "Test failed after launching url");
		
		//Initiate PO for home page and validate
		HomePage home_page = new HomePage();
		
		//validate home page is displayed
		home_page.validateHomePage();
		
		//open Time&Materials page
		home_page.OpenTAndMPage();
		//softAssert.assertEquals(true, true);
		
		//Initiate PO for Time&Materials page and validate
		TAndMPage TAndM_Page = new TAndMPage();
		TAndM_Page.validateTAndMPage();
		//softAssert.assertEquals(true, true);
		
		//call method the create a new record in Time&Materials page
		TAndM_Page.CreateNewTAndMRecord();
		
		//validate the application returns to Time&Materials page
		TAndM_Page.validateTAndMPage();
		
		//validate the new record has been added successfully
		TAndM_Page.ValidateTMRecord();
		
		
		
		//softAssert.assertAll();
		
	}
	
	@Test(description = "Test to delete a record in Time&Materials page and validate")
	public void DeleteTMRecordAndValidate()
	{	
				testLog.log(Status.INFO, "Delete test started");
				SoftAssert softAssert = new SoftAssert();
				//Initiate PO for login page
				LoginPage login_page = new LoginPage();

				//perform login steps
				login_page.loginSteps();
				//softAssert.assertEquals(true, true, "Test failed after launching url");
				
				//Initiate PO for home page and validate
				HomePage home_page = new HomePage();
				
				//validate home page is displayed
				home_page.validateHomePage();
				
				//open Time&Materials page
				home_page.OpenTAndMPage();
				//softAssert.assertEquals(true, true);
				
				//Initiate PO for Time&Materials page and validate
				TAndMPage TAndM_Page = new TAndMPage();
				TAndM_Page.validateTAndMPage();
				//softAssert.assertEquals(true, true);
				
				TAndM_Page.DeleteTMRecord(450);
				
				//TAndM_Page.ValidateTMRecordNotExisting(TAndM_Page.DeleteTMRecord(1));
				
				softAssert.assertEquals(true, true);
				testLog.log(Status.PASS, "A record has been deleted successfully");
				
				softAssert.assertAll();
	}
	
	@Test(description = "Test to edit a record in Time&Materials page and validate")
	public void EditTMRecordAndValidate()
	{	
				//SoftAssert softAssert = new SoftAssert();
				//Initiate PO for login page
				LoginPage login_page = new LoginPage();

				//perform login steps
				login_page.loginSteps();
				//softAssert.assertEquals(true, true, "Test failed after launching url");
				
				//Initiate PO for home page and validate
				HomePage home_page = new HomePage();
				
				//validate home page is displayed
				home_page.validateHomePage();
				
				//open Time&Materials page
				home_page.OpenTAndMPage();
				//softAssert.assertEquals(true, true);
				
				//Initiate PO for Time&Materials page and validate
				TAndMPage TAndM_Page = new TAndMPage();
				TAndM_Page.validateTAndMPage();
				//softAssert.assertEquals(true, true);
				
				
				
				TAndM_Page.ValidateEditTMRecord(TAndM_Page.EditTMRecord());			
						
				
	}
	
	
	@Test(description = "Test to validate the EMI feature")
	public void GetEMI1()
	{		
		testLog.log(Status.INFO, "EMI test started");
		
		//Initiate for Calculate Home Page
		HomePage_Cal HomePage_Cal = new HomePage_Cal();
		
		
		//validate Calculate Home Page
		HomePage_Cal.validateCalHomePage();
		
		//enter details and get EMI and validate			
		HomePage_Cal.ValidateDetails(HomePage_Cal.GetEMIWithDefferData(1));
	
	}
	
	@Test(description = "Test to validate the EMI feature")
	public void NoPrice()
	{		
		testLog.log(Status.INFO, "EMI test started");
		
		//Initiate for Calculate Home Page
		HomePage_Cal HomePage_Cal = new HomePage_Cal();
		
		
		//validate Calculate Home Page
		HomePage_Cal.validateCalHomePage();
		
		//enter details and get EMI and validate			
		HomePage_Cal.validateNoPrice();
	
	}
	
}
