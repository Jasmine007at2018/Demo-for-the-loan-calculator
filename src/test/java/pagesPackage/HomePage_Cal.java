package pagesPackage;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.google.common.base.Function;

import helperPackage.BrowserFactory;
import testPackage.BaseClass;
import utilityPackage.ExcelDataConfig;

public class HomePage_Cal extends BaseClass{
	//BrowserFactory browserObj;	

	//POM constructor
	public HomePage_Cal()
	{
		PageFactory.initElements(BrowserFactory.driver, this);
	}

	
	//Calculate page title
	@FindBy(how = How.XPATH, using = "//span[@class='atcui-page-title']")
	static WebElement calculatePageTitle;
	
	//close
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/a/span")
	static WebElement close;
	
	//Interest Rate
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_55']/div[2]/span/input")
	static WebElement interestRate;
		
	//price
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_5k']/div[2]/span/input")
	static WebElement price;
		
	//price displayed on Estimated Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='price']/span[2]/span")
	static WebElement price_EP;
				
	//price reminder when no price entered
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'cart-item-help-mod contextError')]/div")
	static WebElement price_reminder;
		
	//calculate button
	@FindBy(how = How.XPATH, using = ".//*[@id='loanCalculatorCalculateActionPanel']/span/input")
	static WebElement calculateBtn;
		
	//Estimated Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[1]")
	static WebElement EP;
		
	//EMI
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[2]")
	static WebElement EMI;
		
	//month&rate
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[3]")
	static WebElement monthANDrate;
				
	//Sales Tax Rate
	@FindBy(how = How.XPATH, using = ".//*[@id='salesTaxRate']/span[2]/span")
	static WebElement SalesTaxRate;
				
	//Down Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='downPayment']/span[2]/span")
	static WebElement DownPayment;
				
	//Trade-In Value
	@FindBy(how = How.XPATH, using = ".//*[@id='tradeInValue']/span[2]/span")
	static WebElement TradeInValue;
				
	//Amount Owed on Trade
	@FindBy(how = How.XPATH, using = ".//*[@id='amountOwed']/span[2]/span")
	static WebElement AmountOwedonTrade;
				
	//Total Financed
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_az']/span[2]/span")
	static WebElement TotalFinanced;
				
	//Total Interest
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_b7']/span[2]/span")
	static WebElement TotalInterest;

	//Total Loan
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_bg']/span[2]/span")
	static WebElement TotalLoan;			
	
	
	
	//Method to check if locate calculate home page	
	public void validateCalHomePage()
	{
		String title=null;
		
		
		//BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			// Waiting 30 seconds for close button to be present on the page, checking
			// for its presence once every 1 seconds.
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(BrowserFactory.driver)
		       .withTimeout(30, TimeUnit.SECONDS)
		       .pollingEvery(1, TimeUnit.SECONDS)
		       .ignoring(NoSuchElementException.class);

			WebElement close = fluentWait.until(new Function<WebDriver, WebElement>() {
		    public WebElement apply(WebDriver driver) {
		    return driver.findElement(By.xpath("/html/body/div[4]/div[1]/a/span"));
		     }
		   });
				
		
		title="Auto Loan Calculator";	
		
		//click on close
		close.click();
		
		}catch (Exception e)
		{
			testLog.log(Status.INFO, e.getMessage());
		}
		
		
		//Verification in Calculate Home Page
		if(title.equals(calculatePageTitle.getText()))
		{
			testLog.log(Status.PASS, "locate to Calculate Home Page ");
		}
		else
		{
			testLog.log(Status.FAIL, "not locate to Calculate Home Page ");
		}
		
		assertEquals(title, calculatePageTitle.getText(),"not locate to Calculate Home Page");
		
		
	}
	
	
	
	public int GetEMIWithDefferData(int ID)//ID is defined in ./TestData/TestData.xlsx
	{
		try {
		String title=null;
		int type;
		//prepare the data
		ExcelDataConfig excel = new ExcelDataConfig("./TestData/TestData.xlsx");
		
		//read "type" from "EMIDATA" sheet
		type=new Integer(excel.getData("EMIDATA", ID, 1)).intValue(); 		
		
		//choose month via "type"
		BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[" + type +"]/label")).click();
	
		//send value to Interest Rate
		interestRate.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),excel.getData("EMIDATA", ID, 3));
		
		//send value to Price
		price.sendKeys(excel.getData("EMIDATA", ID, 4));
		
		//click on calculate button
		calculateBtn.click();
		
		
		//BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Waiting 30 seconds for "Estimated Payment" to be present on the page, checking
		// for its presence once every 1 seconds.
					Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(BrowserFactory.driver)
				       .withTimeout(30, TimeUnit.SECONDS)
				       .pollingEvery(1, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class);

					WebElement EP = fluentWait.until(new Function<WebDriver, WebElement>() {
				    public WebElement apply(WebDriver driver) {
				    return driver.findElement(By.xpath(".//*[@id='top-of-text']/span[1]"));
				     }
				   });
		
		
		//validate Estimated Payment is displayed
		title="Estimated Payment";
		
		assertEquals(title, EP.getText(),"Estimated Payment is not displayed");
		
		
		}catch(Exception e)
		
		{
			testLog.log(Status.FAIL, e.getMessage());
		}
		
		return ID;
		
	}
	
	
	//method to validate the EMI and the details
	public void ValidateDetails(int ID)
	{
		
		
		//prepare the data
		ExcelDataConfig excel = new ExcelDataConfig("./TestData/TestData.xlsx");
		
		SoftAssert softAssert = new SoftAssert();
		
		try {
		//verify price
		softAssert.assertEquals(price_EP.getText(), excel.getData("EMIDATA", ID, 5), "The price is wrong");
		//softAssert.assertEquals(price_EP.getText(), "200", "The value is wrong");
		
		//verify Tax Rate
		softAssert.assertEquals(SalesTaxRate.getText(), excel.getData("EMIDATA", ID, 6), "The SalesTaxRate is wrong");
		//verify Down Payment
		softAssert.assertEquals(DownPayment.getText(), excel.getData("EMIDATA", ID, 7), "The DownPayment is wrong");
		//verify Trade-In Value
		softAssert.assertEquals(TradeInValue.getText(), excel.getData("EMIDATA", ID, 8), "The TradeInValue is wrong");
		//verify Amount Owed on Trade
		softAssert.assertEquals(AmountOwedonTrade.getText(), excel.getData("EMIDATA", ID, 9), "The AmountOwedonTrade is wrong");
		//verify EMI
		softAssert.assertEquals(EMI.getText(), excel.getData("EMIDATA", ID, 10), "The Estimated Payment is wrong");
		//verify month&rate
		softAssert.assertEquals(monthANDrate.getText(), excel.getData("EMIDATA", ID, 11), "The monthsANDrate is wrong");
		//verify Total Financed
		softAssert.assertEquals(TotalFinanced.getText(), excel.getData("EMIDATA", ID, 12), "The TotalFinanced is wrong");
		//verify Total Interest
		softAssert.assertEquals(TotalInterest.getText(), excel.getData("EMIDATA", ID, 13), "The TotalInterest is wrong");
		//verify Total Loan
		softAssert.assertEquals(TotalLoan.getText(), excel.getData("EMIDATA", ID, 14), "The TotalLoan is wrong");
				
		softAssert.assertAll();
		}catch(Exception e)
		
		{
			testLog.log(Status.FAIL, e.getMessage());
		}
		
		
	}
	
	public void validateNoPrice()
	{
		try {
				String NoPriceMsg=null;
			
				//click on 24 months
				BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[1]/label")).click();
				
				//send value to interestRate
				interestRate.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"4.5");
				
				//click on calculate button
				calculateBtn.click();
				
				NoPriceMsg="Please fill out the Vehicle Price field.";
				
				//System.out.println(price_reminder.getText());
				testLog.log(Status.INFO, "The actual price reminder is :"+price_reminder.getText());
				
				//validate the reminder when no price entered
				assertEquals(NoPriceMsg, price_reminder.getText(), "price reminder is not displayed");
				
				
			}catch(Exception e)
		{
				testLog.log(Status.INFO, e.getMessage());
		
		}
		
	

	}

}
