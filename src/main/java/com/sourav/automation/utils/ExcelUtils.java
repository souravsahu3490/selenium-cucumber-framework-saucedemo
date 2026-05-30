package com.sourav.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[rowCount - 1][colCount];
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = cell.toString();
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            String value = cell.toString();
            workbook.close();
            fis.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String value) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);
            fis.close();
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}