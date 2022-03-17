package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.base.Throwables;

public class ExcelFileUtility extends WebDriverUtility{
	
	/**
	 * This method will read data from excel sheet and return the value when sheetname row no and 
	 * cell number is specified
	 * @param SheetName
	 * @param rowNo
	 * @param cellNo
	 * @return 
	 * @return
	 * @throws Throwable
	 */

	public String readDataFromExcel(String SheetName, int rowNo, int celNo) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.getCell(celNo);
		 String value = cel.getStringCellValue();
		 return value;
	}
	
	/**
	 * This method will write data into excel sheet
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @param value
	 * @throws Throwable
	 */
	
	public void WriteDataintoExcel(String SheetName, int rowNo, int celNo, String value) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		 Cell cel = ro.createCell(celNo);
		cel.setCellValue(value);
		 FileOutputStream fos = new FileOutputStream(IPathConstants.ExcelPath);
		 wb.write(fos);
	}
	
	/**
	 * This method will return last row number
	 * @param SheetName
	 * @return
	 * @throws Throwable, Throwable 
	 * @throws Throwable
	 * @throws Throwable
	*/
	public int getRowCount(String SheetName) throws Throwable, Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet(SheetName);
		int row = sh.getLastRowNum();
		return row;
	}
	
	/**
	 * This method will read multiple data from excel sheet with the help of sheetname and return 2 dimensional object[][]
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	
	public Object[][] readmultipleDataFromExcel(String SheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		
		Object[][] data= new Object[lastRow][lastCell];
		for(int i= 0; i<lastRow; i++)
		{
			for(int j=0; j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		
		return data;
	}
	
	}























