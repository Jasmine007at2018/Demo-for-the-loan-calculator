package cucumberStepDefinitionPackage;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helperPackage.BrowserFactory;

public class PriceReminderStepDefinition {
	
	//BrowserFactory browserObj;
	
	@Given("^I have launched the browser and entered the URL of loan calculator$")
	public void i_have_launched_the_browser_and_entered_the_URL_of_loan_calculator() throws Throwable {
		//Initiate driver and launch url
				BrowserFactory.startBrowser("chrome");
	}

	@When("^I click on (\\d+) months, input Interest Rate, and click on Calculate button$")
	public void i_click_on_months_input_Interest_Rate_and_click_on_Calculate_button(int arg1) throws Throwable {
		
		BrowserFactory.driver.findElement(By.xpath("/html/body/div[4]/div[1]/a/span")).click();
		BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[1]/label")).click();
		
		//send value to interestRate
		BrowserFactory.driver.findElement(By.xpath(".//*[@id='j_id_55']/div[2]/span/input")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"4.5");
		
		//click on calculate button
		BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorCalculateActionPanel']/span/input")).click();
		
		//time wait
		BrowserFactory.driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	}

	@Then("^I should see Price reminder$")
	public void i_should_see_Price_reminder() throws Throwable {
		//check the reminder for price
		String msg=BrowserFactory.driver.findElement(By.xpath(".//*[@class='cart-item-help-mod contextError']/div[@class='cart-item-error-text']")).getText();
		assertTrue("price reminder is not displayed", msg.equals("Please fill out the Vehicle Price field."));
		
		
	}


}
