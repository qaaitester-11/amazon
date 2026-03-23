package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import pages.LoginPage;

public class AdminTest extends BaseTest {
	
	 private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(AdminTest.class);


	// This is a placeholder for admin-related tests.
	// You can add test methods here to verify admin functionalities such as user
	// management, product management, etc.

	// test data - exce
	// log4j library for logging

	@Test(dataProvider = "getData")
	public void Verifyalreadyadmin(Map<String, String> data) throws FileNotFoundException, IOException, InterruptedException {

		
		
		logger.info("Starting test: Verifyalreadyadmin with data: " + data);

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver(), getExtentTest());
		loginPage.enterUsername(data.get("Employeename"));
		Thread.sleep(2000);
		loginPage.enterPassword(data.get("status"));
		Thread.sleep(2000);
		loginPage.clickLogin();
		Thread.sleep(10000);
		
		logger.info("Completed login steps for test: Verifyalreadyadmin with data: " + data);
		
		// loginPage.verifyLogin("test user");

	}

}
