package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import constants.Constants;
import utils.Config;
import utils.Excelutil;

public class BaseTest extends ConfigProperty {

	private WebDriver driver;

	

	

	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException {
		
		Config.getInstance().loadProperties();
		Excelutil.readExcel();
		
		
		
	}
	// create a method to initialize the driver based on the browser parameter with
	// itest contect

	@BeforeMethod
	public void setup(ITestContext context, Method method) throws FileNotFoundException, IOException {

	//	Map<String, String> values = context.getCurrentXmlTest().getLocalParameters();
	//	String browser = values.get("browser");

		if ("chrome".equalsIgnoreCase(Constants.CHROME)) {
			driver = new ChromeDriver();
		   
			
		} else if (Constants.FIREFOX.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			
		} else {
			driver = new ChromeDriver();
			throw new IllegalArgumentException("Unsupported browser: " );
		}
		
		DriverFactory.setDriver(driver);
		 
		DriverFactory.getDriver().manage().window().maximize();
		System.out.println("Navigating to URL: " + ConfigProperty.getProperty("app.url"));
		DriverFactory.getDriver().get(ConfigProperty.getProperty("app.url"));

	}
	
	
	@AfterMethod
	public void tearDown() {
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}
	

	@org.testng.annotations.DataProvider(name = "getData")
	public static Object[][] getData(Method key) throws FileNotFoundException, IOException {
	
	//	Excelutil.readExcel();
		
		Object[][] data = new Object[1][1];
		
		data[0][0] = Excelutil.testData.get(key.getName());
		
		return data;
		
	
		
	}

}
