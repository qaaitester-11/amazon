package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;

import constants.Constants;
import utils.Config;
import utils.Excelutil;
import utils.ReportUtil;

public class BaseTest extends ConfigProperty {

	

	private ThreadLocal<ExtentTest> th = new ThreadLocal<>();
	private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException {

		Config.getInstance().loadProperties();
		Excelutil.readExcel();
		ReportUtil.initializeReport();

	}
	// create a method to initialize the driver based on the browser parameter with
	// itest contect

	@BeforeMethod
	public void setup(ITestContext context, Method method) throws FileNotFoundException, IOException {

		Map<String, String> values = context.getCurrentXmlTest().getLocalParameters();
		String browser = values.get("browser");

		String run = System.getProperty("runmode");

		if (run != null && run.equalsIgnoreCase("grid")) {
			
			WebDriver driver;

			if (browser.equalsIgnoreCase(Constants.CHROME)) {
				driver = new ChromeDriver();
				driverThreadLocal.set(driver);
				

			} else if (Constants.FIREFOX.equalsIgnoreCase(browser)) {
				driver = new FirefoxDriver();
				driverThreadLocal.set(driver);

			} else {
				driver = new ChromeDriver();
				driverThreadLocal.set(driver);
				throw new IllegalArgumentException("Unsupported browser: ");
			}

		} else {

			RemoteWebDriver driver;
			
			if (browser.equalsIgnoreCase(Constants.CHROME)) {

				ChromeOptions chromeOptionsManager = new ChromeOptions();

				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptionsManager);
				driverThreadLocal.set(driver);

			} else if (Constants.FIREFOX.equalsIgnoreCase(browser)) {

				FirefoxOptions firefoxOptionsManager = new FirefoxOptions();

				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptionsManager);
				driverThreadLocal.set(driver);

			} else {

				ChromeOptions chromeOptionsManager = new ChromeOptions();

				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptionsManager);
				driverThreadLocal.set(driver);

				throw new IllegalArgumentException("Unsupported browser: ");
			}

		}

		ExtentTest extentTest = ReportUtil.getExtent().createTest(method.getName());
		extentTest.info("Starting test: " + method.getName());
		extentTest.assignCategory(context.getCurrentXmlTest().getName());
		extentTest.assignDevice(browser);

	//	DriverFactory.setDriver(driver);

		th.set(extentTest);

		driverThreadLocal.get().manage().window().maximize();
		System.out.println("Navigating to URL: " + ConfigProperty.getProperty("app.url"));
		driverThreadLocal.get().get(ConfigProperty.getProperty("app.url"));

	}

	@AfterMethod
	public void tearDown() {
		if (DriverFactory.getDriver() != null) {
			driverThreadLocal.get().quit();
		}
	}

	@org.testng.annotations.DataProvider(name = "getData")
	public static Object[][] getData(Method key) throws FileNotFoundException, IOException {

		// Excelutil.readExcel();

		Object[][] data = new Object[1][1];

		data[0][0] = Excelutil.testData.get(key.getName());

		return data;

	}

	@AfterSuite
	public void flushReport() {
		ReportUtil.flushReport();
	}

	public ExtentTest getExtentTest() {
		return th.get();
	}

}
