package pagesPackage;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import helperPackage.BrowserFactory;
import utilityPackage.ConfigReader;
import utilityPackage.ExcelDataConfig;
import utilityPackage.ScreenShot;

public class HomePage {
	
	//POM constructor
	public HomePage()
	{
		PageFactory.initElements(BrowserFactory.driver, this);
	}
	
	//Add new Job button
	@FindBy(how = How.ID, using = "addnewjob")
	//@FindBy(how = How.XPATH, using = "//a[@href='/Job']")
	static WebElement addJobButton;
	
	//Administration Tab
	@FindBy(how=How.XPATH, using="//a[contains(.,'Administration ')]")
	static WebElement administrationTab;
	
	//Time&Materials Tab
	@FindBy(how=How.XPATH, using="//a[contains(.,'Time & Materials')]")
	static WebElement tAndMTab;
	
	//items of customers page
	//Customers option
	@FindBy(how=How.XPATH, using="//a[contains(.,'Customers')]")
	static WebElement customersTab;
	
	//Create New button
	@FindBy(how=How.XPATH,using="//a[contains(.,'Create New')]")
	static WebElement createNewBtn;
	
	//Name
	@FindBy(how = How.ID, using = "Name")
	static WebElement name;
	
	//items of Edit contact frame
	//Edit Contact Button
	@FindBy(how=How.ID,using="EditContactButton")
	static WebElement editContactBtn;
	
	//First Name
	@FindBy(how = How.ID, using = "FirstName")
	static WebElement firstName;
	
	//Last Name
	@FindBy(how = How.ID, using = "LastName")
	static WebElement lastName;
	
	//Prefered Name
	@FindBy(how = How.ID, using = "PreferedName")
	static WebElement preferedName;
	
	//Phone
	@FindBy(how = How.ID, using = "Phone")
	static WebElement phone;
	
	//Mobile
	@FindBy(how = How.ID, using = "Mobile")
	static WebElement mobile;
	
	//Email
	@FindBy(how = How.ID, using = "email")
	static WebElement email;
		
	//Fax
	@FindBy(how = How.ID, using = "Fax")
	static WebElement fax;
	
	//Street
	@FindBy(how = How.ID, using = "Street")
	static WebElement street;
		
	//City
	@FindBy(how = How.ID, using = "City")
	static WebElement city;
		
	//Postcode
	@FindBy(how = How.ID, using = "Postcode")
	static WebElement postcode;
		
	//Country
	@FindBy(how = How.ID, using = "Country")
	static WebElement country;
		
	//Save Contact Button
	@FindBy(how = How.ID, using = "submitButton")
	static WebElement submitButton;
	
	//Save button on Customer page
	@FindBy(how=How.ID,using="submitButton")
	static WebElement saveBtn;
	
	
	//items of Billing Contact
	
	//Edit billing contact button
	@FindBy(how=How.ID,using="EditBillingContactButton")
	static WebElement editBCBtn;
	
		//First Name
		@FindBy(how = How.ID, using = "FirstName")
		static WebElement firstName_BC;
		
		//Last Name
		@FindBy(how = How.ID, using = "LastName")
		static WebElement lastName_BC;
		
		//Prefered Name
		@FindBy(how = How.ID, using = "PreferedName")
		static WebElement preferedName_BC;
		
		//Phone
		@FindBy(how = How.ID, using = "Phone")
		static WebElement phone_BC;
		
		//Mobile
		@FindBy(how = How.ID, using = "Mobile")
		static WebElement mobile_BC;
		
		//Email
		@FindBy(how = How.ID, using = "email")
		static WebElement email_BC;
			
		//Fax
		@FindBy(how = How.ID, using = "Fax")
		static WebElement fax_BC;
		
		//Street
		@FindBy(how = How.ID, using = "Street")
		static WebElement street_BC;
			
		//City
		@FindBy(how = How.ID, using = "City")
		static WebElement city_BC;
			
		//Postcode
		@FindBy(how = How.ID, using = "Postcode")
		static WebElement postcode_BC;
			
		//Country
		@FindBy(how = How.ID, using = "Country")
		static WebElement country_BC;
			
		//Save Contact Button
		@FindBy(how = How.ID, using = "submitButton")
		static WebElement submitButton_BC;
		
		//GST text box
		@FindBy(how=How.ID,using="GST")
		static WebElement GST;

	//Create New button
	//@FindBy(how=How.XPATH,using="//a[contains(.,'Create New')]")
	//static WebElement createNewBtn;
	
	//Method to check if home page is displayed
	public void validateHomePage()
	{
		//Verification
		BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		boolean verifyMsg = addJobButton.isDisplayed();
		
		if(verifyMsg == true)
		{
			System.out.println("Test Pass, login successful");
		}
		else
		{
			System.out.println("Test Fail, login failed");

		}
	}
	
	
	//Method to open Time&Materials page
	public void OpenTAndMPage()
	{
		//click Administration->Time&Materials open the Time&Materials page
		administrationTab.click();
		System.out.println("clicked on administration Tab");
				
		tAndMTab.click();
		System.out.println("clicked on Time&Materials Tab");
	}
	
	//Method to create a new customer record
	public void CreateNewCustomer()
	{
		//click Administration->Customers->Create New button to open a customer edit page
		administrationTab.click();
		System.out.println("clicked on administration Tab");
		
		customersTab.click();
		System.out.println("clicked on customersTab");
		
		
		createNewBtn.click();
		System.out.println("clicked on createNewBtn");
	
		
		String parentWindowId = BrowserFactory.driver.getWindowHandle();
		
		//prepare the data 
		ExcelDataConfig excel=new ExcelDataConfig(ConfigReader.getExcelPath());

		//send value to the Name textbox 
		name.sendKeys(excel.getData("Customers", 1,0));
		System.out.println("Name entered");

		//click on Edit Contact button to open the Edit Contact window
		editContactBtn.click();
		System.out.println("clicked on editContact button");		
			
				
		//find the Edit Contact frame
		//WebElement FrameElement=BrowserFactory.driver.findElement(By.xpath("/html/body/div[6]/div[2]/iframe")); 
		
		WebElement FrameElement=BrowserFactory.driver.findElement(By.xpath("//*[@id=\"contactDetailWindow\"]/iframe"));
		
		//*[@id="contactDetailWindow_wnd_title"]  //*[@id="contactDetailWindow"]/iframe
		BrowserFactory.driver.switchTo().frame(FrameElement);
		System.out.println("go for the Edit Contact frame");
		
		//Thread.sleep(1000);
		
				try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            e.printStackTrace(); 
		        }
		
		//send values to the text boxes on Edit Contact window
		firstName.sendKeys(excel.getData("Customers", 2, 1));		
		//firstName.sendKeys("Jam");
		System.out.println("FirstName entered");
		
		lastName.sendKeys(excel.getData("Customers", 2, 2));
		System.out.println("lastName entered");		
		
		preferedName.sendKeys(excel.getData("Customers", 2, 3));
		phone.sendKeys(excel.getData("Customers", 2, 4));
		mobile.sendKeys(excel.getData("Customers", 2, 5));
		email.sendKeys(excel.getData("Customers", 2, 6));
		fax.sendKeys(excel.getData("Customers", 2, 7));
		street.sendKeys(excel.getData("Customers", 2, 8));
		city.sendKeys(excel.getData("Customers", 2, 9));
		postcode.sendKeys(excel.getData("Customers", 2, 10));
		country.sendKeys(excel.getData("Customers", 2, 11));
		
		//click on save button on Edit Contact page
		submitButton.click();	
		
		
		//Thread.sleep(1000);
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }

		//back to customer edit window
		BrowserFactory.driver.switchTo().window(parentWindowId);
		
		//time wait
		BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//start to edit billing contact
		//click on Edit Billing Contact button
		editBCBtn.click();
		
		//find the Edit Billing Contact frame
		//WebElement FrameElement=BrowserFactory.driver.findElement(By.xpath("/html/body/div[6]/div[2]/iframe")); 
				
		WebElement FrameElement_BC=BrowserFactory.driver.findElement(By.xpath("//*[@id=\'contactDetailWindow\']/iframe"));
	
		BrowserFactory.driver.switchTo().frame(FrameElement_BC);
		System.out.println("go for the Edit Billing Contact frame");
				
		//send data to text boxes in Billing Contact frame
		
		//firstName_BC.sendKeys("Jam");
		firstName_BC.sendKeys(excel.getData("Customers", 7, 1));	
		System.out.println("FirstName entered");
		
		lastName_BC.sendKeys(excel.getData("Customers", 7, 2));
		System.out.println("lastName entered");		
		
		preferedName_BC.sendKeys(excel.getData("Customers", 7, 3));
		phone_BC.sendKeys(excel.getData("Customers", 7, 4));
		mobile_BC.sendKeys(excel.getData("Customers", 7, 5));
		email_BC.sendKeys(excel.getData("Customers", 7, 6));
		fax_BC.sendKeys(excel.getData("Customers", 7, 7));
		street_BC.sendKeys(excel.getData("Customers", 7, 8));
		city_BC.sendKeys(excel.getData("Customers", 7, 9));
		postcode_BC.sendKeys(excel.getData("Customers", 7, 10));
		country_BC.sendKeys(excel.getData("Customers", 7, 11));
		
		//click on save button on Edit Contact page
		submitButton_BC.click();	
		
		//back to customer edit window
		BrowserFactory.driver.switchTo().window(parentWindowId);
		
		//send data to GST text box
		GST.sendKeys(excel.getData("Customers", 10, 0));
		
		//try
		//{
			boolean verifySaveBtn = saveBtn.isSelected();
			if(verifySaveBtn==true)
			{
			//click on Save button on Customer page
			saveBtn.click();
			System.out.println("New customer saved");
			}
		
			else
			{System.out.println("New customer can not be saved");}
		
			//assert.assertTrue(verifySaveBtn, "Save button is not clickable");
			assertTrue("Save button is not clickable", verifySaveBtn);
//		  }
//		
//		 catch (Exception e)
//		{
//	            e.printStackTrace(); 
//		}
//		
		
		//screen shot
		ScreenShot.captureScreenshot(BrowserFactory.driver, "CreateNewCustomer");
//		
//		//call method to verify if return to Customer page successfully
//		validateCustomerPage();
		
		
	}
	
	//Method to check if Customer page is displayed
		public void validateCustomerPage()
		{
			//Verification
			BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			try
			{
				String msgOfClient=BrowserFactory.driver.findElement(By.xpath("//*[@id='container']/p/a")).getText();//find the element including text "Client"
				boolean verifyMsg=msgOfClient.contains("/Client/Create?header=True");
					
						
				if(verifyMsg == true)
				{
				System.out.println("Test Pass, retrun to Customer page successful");
				}
				else
				{
					System.out.println("Test Fail, not retrun to Customer page");

				}
			
			}
			
			 catch (Exception e)
			{
		            e.printStackTrace(); 
			}
			
			
			
		}
		



}
