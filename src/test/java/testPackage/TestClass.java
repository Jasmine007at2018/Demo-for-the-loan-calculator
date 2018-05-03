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

import utilityPackage.ConfigReader;

public class TestClass extends BaseClass {
	
	
	
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

	
	
	@Test(description = "Test to validate price reminder when no price is entered")
	public void NoPrice()
	{		
		testLog.log(Status.INFO, "NoPrice test started");
		
		//Initiate for Calculate Home Page
		HomePage_Cal HomePage_Cal = new HomePage_Cal();
		
		
		//validate Calculate Home Page
		HomePage_Cal.validateCalHomePage();
		
		//enter details and get EMI and validate			
		HomePage_Cal.validateNoPrice();
		testLog.log(Status.PASS, "The price reminder is displayed when no price entered");
		
	
	}
	
}
