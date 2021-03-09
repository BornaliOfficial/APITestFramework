package com.kosmos.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kosmos.core.PropertyReader;

public class ExcelHelper extends FileHandler {
	private PropertyReader configFile = null;
//	private Cell cell;
	private String fileName, sheetName;
	private Workbook excelWorkBook = null;
	private Sheet excelSheet = null;
	private String excelFileName = null;
	private String excelSheetName = null;
	private String testDataDir = null;
	ArrayList<String> cellValues = new ArrayList<String>();

	public ExcelHelper() {
		configFile = new PropertyReader("config.properties");
		testDataDir = configFile.getPropertyValue("TEST_DATA_DIR");
		excelFileName = configFile.getPropertyValue("EXCEL_FILE_NAME") + ".xlsx";
		excelSheetName = configFile.getPropertyValue("SHEET_NAME");
		this.fileName = excelFileName;
		this.sheetName = excelSheetName;
	}

	public ArrayList<String> readExcel() throws Exception {

		// create an object of File class to open xlsx file
		File excelFile = createFile(testDataDir + File.separator + fileName);
		// create an object of FileInputStream class to read excel file
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// if it is xlsx file then create object of XSSFWorkbook class
			try {
				excelWorkBook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			try {
				excelWorkBook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// read sheet inside the workbook by its name
		excelSheet = excelWorkBook.getSheet(sheetName);

		// find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();

//		// create a loop over all the rows of excel file to read it
//		for (int i = 1; i < rowCount+1; i++) {
//			Row row = excelSheet.getRow(i);
//			// Create a loop to get cell values in a row
//			for (int j = 0; j < row.getLastCellNum(); j++) {
//				// get the list of excel data
//				cellValues.add(row.getCell(j).getStringCellValue());
//			}
//		}
//		return cellValues;
//	}
		String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(regex);
	 // create a loop over all the rows of excel file to read it
		 for (int i = 1; i < rowCount+1; i++) {
		     Row row = excelSheet.getRow(i);
		   // Create a loop to get cell values in a row
		     for (int j = 0; j < row.getLastCellNum(); j++) {
		  // get the list of excel data
		        String url = row.getCell(j).getStringCellValue();
		        Matcher matcher = pattern.matcher(url);
		        if(matcher.matches())
		           cellValues.add(url);
		     }
		 }
		        return cellValues;
	}

//	public static void main(String[] args) throws Exception {
//		ExcelHelper obj = new ExcelHelper();
//		System.out.println(obj.readExcel());
//	}
}