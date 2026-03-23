package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import base.ConfigProperty;
import base.DriverFactory;
import pages.LoginPage;

public class LoginTest  extends BaseTest {
	
	
	@Test
	public void loginTest2() throws InterruptedException, FileNotFoundException, IOException {
		
		getExtentTest().info("Starting login test");
			
		LoginPage loginPage = new LoginPage(getDriver(), getExtentTest());
		loginPage.enterUsername(ConfigProperty.getProperty("username"));
		loginPage.enterPassword(ConfigProperty.getProperty("password"));
		loginPage.clickLogin();
		Thread.sleep(5000);
		//loginPage.verifyLogin("test user");
		
		
		
	}
	
	
	
	
	

}
