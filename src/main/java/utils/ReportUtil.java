package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {

	private static ExtentReports extent;

	public static void initializeReport() {

		extent = new ExtentReports();

		String reportPath = System.getProperty("user.dir") + "/report/extentReport.html";

		ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));

		spark.config().setReportName("Amazon Test Report");
		spark.config().setDocumentTitle("Amazon Test Report");

		extent.attachReporter(spark);

	}

	public static ExtentReports getExtent() {
		return extent;
	}
	
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}

}
