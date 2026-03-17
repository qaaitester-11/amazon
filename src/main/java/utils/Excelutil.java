package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.LoggerFactory;

public class Excelutil {
	
	
	 private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(Excelutil.class);
	 
	//log4j2
	//Debug, info, warn, error, fatal

	public static Map<String, Map<String, String>> testData = new java.util.HashMap<>();

	public static void readExcel()
			throws EncryptedDocumentException, FileNotFoundException, IOException {
		// Code to read Excel file using Apache POI or any other library
		// This is a placeholder for the actual implementation
	//	System.out.println("Reading Excel file from: " + filePath);
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/app.xlsx";

		Workbook wb = WorkbookFactory.create(new FileInputStream(new File(path)));

		Sheet sheet = wb.getSheet("admin");

		// headers row
		Row row = sheet.getRow(0);

		int rowsize = sheet.getPhysicalNumberOfRows();

		for (int i = 1; i < rowsize; i++) {

			Map<String, String> data = new java.util.HashMap<>();

			Row currentRow = sheet.getRow(i);

			for (int j = 0; j < row.getLastCellNum(); j++) {

				((Logger) logger).debug("Processing row " + i + ", column " + j);
				
				String key = formData(row.getCell(j));
				String value = formData(currentRow.getCell(j));
				data.put(key, value);
			}

			testData.put(data.get("TestcaseName"), data);

		}

	}

	public static String formData(Cell cell) {

		DataFormatter formatter = new DataFormatter();

		return formatter.formatCellValue(cell);

	}

	
	public static Map<String, String> getTestData(String testCaseName) {
		
		return testData.get(testCaseName);
	}
	



}
