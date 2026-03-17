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
			
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigProperty.getProperty("username"));
		loginPage.enterPassword(ConfigProperty.getProperty("password"));
		loginPage.clickLogin();
		Thread.sleep(10000);
		//loginPage.verifyLogin("test user");
		
		
		
	}
	
	
	
	
	

}
