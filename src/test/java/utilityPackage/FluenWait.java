package utilityPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;

import com.google.common.base.Function;

import helperPackage.BrowserFactory;

public class FluenWait {
	
	//Method to wait
	public static void wait(final String xpath,int timeout,int pollingtime)
	{
		// Waiting 30 seconds for close button to be present on the page, checking
					// for its presence once every 1 seconds.
					Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(BrowserFactory.driver)
				       .withTimeout(timeout, TimeUnit.SECONDS)
				       .pollingEvery(pollingtime, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class);

					WebElement elementName = fluentWait.until(new Function<WebDriver, WebElement>() {
				    public WebElement apply(WebDriver driver) {
				    return driver.findElement(By.xpath(xpath));
				     }
				   });
					
					
	}
}
