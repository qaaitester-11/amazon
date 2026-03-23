package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BasePage;
import tests.AdminTest;
import utils.screenshotUtil;

public class LoginPage extends BasePage{
	
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(AdminTest.class);


	public LoginPage(WebDriver driver, ExtentTest extentTest) {
		super(driver, extentTest);
		// TODO Auto-generated constructor stub
	}

	@org.openqa.selenium.support.FindBy(name = "username")
	private org.openqa.selenium.WebElement usernameInput;

//	@org.openqa.selenium.support.FindBy(name = "password")
//	private org.openqa.selenium.WebElement passwordInput;

	@org.openqa.selenium.support.FindBy(css = "button[type='submit']")
	private org.openqa.selenium.WebElement loginButton;

	@org.openqa.selenium.support.FindBy(xpath = "//p[text()='test user']")
	private org.openqa.selenium.WebElement user;

	By passwordInput = By.name("password");

	// p[text()='manda user']
	// a[text()='Logout']

	By logoutLink = By.xpath("//a[text()='Logout']");

	public void enterUsername(String username) throws FileNotFoundException, IOException {

		logger.debug("Entering username: " + username);
		enterText(usernameInput,username);
		// enterText(ExpectedConditions.visibilityOf(usernameInput), username, 10);

	}

	public void enterPassword(String password) {

		enterText(passwordInput, password);

	}

	public void clickLogin() throws InterruptedException {

		clickElement(loginButton);
		
		String screenshotPath = screenshotUtil.captureScreenshot(getDriver(), "LoginPage");
		
		getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

		getExtentTest().log(Status.PASS, "Clicked login button");
		
		Thread.sleep(5000);
	}

	public void verifyLogin(String expectedUsername) {

		getexplicitWait().until(ExpectedConditions.visibilityOf(user));

		Assert.assertEquals(user.getText(), expectedUsername, "Logged in username does not match expected username.");

	}

	public void clickLogout() throws InterruptedException {

		clickElement(user);

		Thread.sleep(7000);

		clickElement(getDriver().findElement(logoutLink));

		// clickElement(logoutLink, 5);

	}

}
