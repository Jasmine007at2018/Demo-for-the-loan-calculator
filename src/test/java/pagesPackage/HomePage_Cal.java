package pagesPackage;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

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
	
//	//Add new Job button
//	@FindBy(how = How.ID, using = "addnewjob")
//	static WebElement addJobButton;
//	
	
	//Calculate page title
	@FindBy(how = How.XPATH, using = "//span[@class='atcui-page-title']")
	static WebElement atcuiPageTitle;
	
	//close
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/a/span")
	static WebElement close;
	
	//month
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-loanCalculatorMonths-loanCalculatorLengthOfLoan']/tbody/tr/td[1]/label")
	static WebElement month;
	
	
	//Interest Rate
		@FindBy(how = How.ID, using = "j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_55-loanCalculatorInterestRate")
		static WebElement interestRate;
		
		//price
		@FindBy(how = How.ID, using = "j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_5k-priceInput")
		static WebElement price;
		
		//price displayed on Estimated Payment
				@FindBy(how = How.XPATH, using = ".//*[@id='price']/span[2]/span")
				static WebElement price_EP;
				
		//price reminder when no price entered
		@FindBy(how = How.XPATH, using = "//*[@id='price-help-content']")
		static WebElement price_reminder;
		
		//calculate button
		@FindBy(how = How.ID, using = "j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_7m-loanCalculatorCalculateActionPanel-j_id_7r-j_id_7r")
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
		String title="";
		
		
		BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		title="Auto Loan Calculator";
		
		//click on close
		close.click();
		
		//Verification in Calculate Home Page
		if(title.equals(atcuiPageTitle.getText()))
		{
			testLog.log(Status.PASS, "locate to Calculate Home Page ");
		}
		else
		{
			testLog.log(Status.FAIL, "not locate to Calculate Home Page ");
		}
		
		assertEquals(title, atcuiPageTitle.getText(),"not locate to Calculate Home Page");
		
		
	}
	
	public int GetEMIWithDefferData(int type)//type is defined in ./TestData/TestData.xlsx
	{
		String title="";
		
		//prepare the data
		ExcelDataConfig excel = new ExcelDataConfig("./TestData/TestData.xlsx");
		
		//choose month via "type"
		BrowserFactory.driver.findElement(By.xpath(".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-loanCalculatorMonths-loanCalculatorLengthOfLoan']/tbody/tr/td[" + type +"]/label")).click();
	
		//send value to Interest Rate
		interestRate.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),excel.getData("EMIDATA", type, 2));
		
		//send value to Price
		price.sendKeys(excel.getData("EMIDATA", type, 3));
		
		//click on calculate button
		calculateBtn.click();
		
		

		
		//validate Estimated Payment is displayed
		BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		title="Estimated Payment";
		
		assertEquals(title, EP.getText(),"Estimated Payment is not displayed");
		
		return type;
		
	}
	
	
	//method to validate the EMI and the details
	public void ValidateDetails(int type)
	{
		
		
		//prepare the data
		ExcelDataConfig excel = new ExcelDataConfig("./TestData/TestData.xlsx");
		
		SoftAssert softAssert = new SoftAssert();
		
		try {
		//verify price
		//softAssert.assertEquals(price_EP.getText(), excel.getData("EMIDATA", type, 4), "The value is wrong");
		softAssert.assertEquals(price_EP.getText(), "200", "The value is wrong");
		
		//verify Tax Rate
		softAssert.assertEquals(SalesTaxRate.getText(), excel.getData("EMIDATA", type, 5), "The value is wrong");
		//verify Down Payment
		softAssert.assertEquals(DownPayment.getText(), excel.getData("EMIDATA", type, 6), "The value is wrong");
		//verify Trade-In Value
		softAssert.assertEquals(TradeInValue.getText(), excel.getData("EMIDATA", type, 7), "The value is wrong");
		//verify Amount Owed on Trade
		softAssert.assertEquals(AmountOwedonTrade.getText(), excel.getData("EMIDATA", type, 8), "The value is wrong");
		//verify EMI
		softAssert.assertEquals(EMI.getText(), excel.getData("EMIDATA", type, 9), "The value is wrong");
		//verify month&rate
		softAssert.assertEquals(monthANDrate.getText(), excel.getData("EMIDATA", type, 10), "The value is wrong");
		//verify Total Financed
		softAssert.assertEquals(TotalFinanced.getText(), excel.getData("EMIDATA", type, 11), "The value is wrong");
		//verify Total Interest
		softAssert.assertEquals(TotalInterest.getText(), excel.getData("EMIDATA", type, 12), "The value is wrong");
		//verify Total Loan
		softAssert.assertEquals(TotalLoan.getText(), excel.getData("EMIDATA", type, 13), "The value is wrong");
				
		softAssert.assertAll();
		}catch(Exception e)
		
		{
			testLog.log(Status.FAIL, e.getMessage());
		}
		
		
	}
	
	public void validateNoPrice()
	{
		try {
				String NoPriceMsg="";
			
				//click on 24 months
				BrowserFactory.driver.findElement(By.xpath(".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-loanCalculatorMonths-loanCalculatorLengthOfLoan']/tbody/tr/td[1]/label")).click();
				
				//send value to interestRate
				interestRate.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"4.5");
				
				//click on calculate button
				calculateBtn.click();
				
				NoPriceMsg="Please fill out the Vehicle Price field.";
				System.out.println(price_reminder.getText());
				//System.out.println(BrowserFactory.driver.findElement(By.xpath(".//*[@id='price-help-content']/div")).getText());
				//assertEquals(NoPriceMsg,BrowserFactory.driver.findElement(By.xpath(".//*[@id='price-help-content']/div")).getText(), "price reminder is not displayed");
				assertEquals(NoPriceMsg, price_reminder.getText(), "price reminder is not displayed");
				
				testLog.log(Status.PASS, "price reminder is displayed");
				
			}catch(Exception e)
		{
				testLog.log(Status.INFO, e.getMessage());
		
		}
		
	

	}

}
