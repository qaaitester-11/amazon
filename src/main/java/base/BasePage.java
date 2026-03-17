package base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.Webelement;

public class BasePage extends ConfigProperty {
	
	
	 private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(BasePage.class);

	private WebDriver driver;
	private WebDriverWait explicitWait;
	private FluentWait<WebDriver> fluentwait;

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getexplicitWait() {

		return explicitWait;

	}

	public BasePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		explicitWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		fluentwait = new org.openqa.selenium.support.ui.FluentWait<>(driver)
				.withTimeout(java.time.Duration.ofSeconds(20)).pollingEvery(java.time.Duration.ofSeconds(2))
				.ignoring(org.openqa.selenium.NoSuchElementException.class);

	}

	// write code 
	public void enterText(org.openqa.selenium.WebElement element, String text) {

		try {

			explicitWait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);

		} catch (Exception e) {

			logger.error("Exception while entering text: " + e.getMessage(), e);
			logger.fatal("Fatal error while entering text: " + e.getMessage(), e);
			//System.out.println("Exception while entering text: " + e.getMessage());
			throw new Webelement("element not found",  new ElementClickInterceptedException("karshak"));

		}

	}
	
	//find a element by id
	

	public void enterText(By by, String text) {

		try {

			WebElement ele = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));

			ele.clear();
			ele.sendKeys(text);

		} catch (Exception e) {

			throw new WebDriverException("element not found");

		}

	}

	public void clickElement(org.openqa.selenium.WebElement element) {

		try {

			fluentwait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(element));
			element.click();

		} catch (org.openqa.selenium.StaleElementReferenceException e) {

			throw new RuntimeException("Element is stale: " + element.toString(), e);
		}

	}

	public void clickElement(By by, int timeoutInSeconds) {

		WebElement el = fluentwait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(by));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", by);

		el.click();

	}

}
