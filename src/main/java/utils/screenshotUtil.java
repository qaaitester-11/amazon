package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.DriverFactory;

public class screenshotUtil {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		// Implement screenshot capture logic here
		// This is a placeholder implementation and should be replaced with actual code
		// to capture and save a screenshot
		String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + "_"
				+ System.currentTimeMillis() + ".jpg";
		// Code to capture and save the screenshot goes here

		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture the screenshot and save it to the specified path
		try {

			File SRC = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(SRC, new File(screenshotPath));

			return screenshotPath;

		} catch (java.io.IOException e) {
			e.printStackTrace();

		}
		return screenshotPath;

	}
}
