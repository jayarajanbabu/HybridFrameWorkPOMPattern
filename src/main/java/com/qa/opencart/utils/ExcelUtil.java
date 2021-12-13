package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
//	private static final String TEST_DATA_SHEET_PATH="./src/test/resources/TestData/TestData_OpenCart.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		
		if (envName==null) {
			System.out.println("Running with PROD data");
			try {
				ip = new FileInputStream("./src/test/resources/TestData/TestData_OpenCart.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Running with data : "+envName);
			try {
			switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/TestData/QA_OpenCart.xlsx");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/TestData/Dev_OpenCart.xlsx");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/TestData/Stage_OpenCart.xlsx");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/TestData/UAT_OpenCart.xlsx");
				break;
			default:
				System.out.println("Please pass the correct environment");
				break;
			}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
			try {
				book = WorkbookFactory.create(ip);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
						
			
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int row=0; row<sheet.getLastRowNum();row++) {
			for (int col=0; col<sheet.getRow(0).getLastCellNum();col++) {
				data[row][col] = sheet.getRow(row+1).getCell(col).toString();
			}
		}
		return data;
	}
	

}
