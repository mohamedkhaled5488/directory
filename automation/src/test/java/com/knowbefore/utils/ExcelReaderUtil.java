package com.knowbefore.utils;

import com.knowbefore.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReaderUtil {

    private static final Logger log = LogManager.getLogger(ExcelReaderUtil.class);
    private static final ConfigReader config = ConfigReader.getInstance();

    private ExcelReaderUtil() {}

    public static List<Map<String, String>> readExcelData(String fileName, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        String filePath = config.getTestDataPath() + fileName;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.error("Sheet '{}' not found in file: {}", sheetName, filePath);
                return data;
            }

            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(getCellValue(cell));
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Map<String, String> rowData = new LinkedHashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    rowData.put(headers.get(j), cell != null ? getCellValue(cell) : "");
                }
                data.add(rowData);
            }
            log.info("Read {} rows from sheet '{}' in '{}'", data.size(), sheetName, fileName);
        } catch (IOException e) {
            log.error("Failed to read Excel file: {}", filePath, e);
        }
        return data;
    }

    public static Object[][] getTestData(String fileName, String sheetName) {
        List<Map<String, String>> data = readExcelData(fileName, sheetName);
        Object[][] result = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    public static String getCellValue(String fileName, String sheetName, int row, int col) {
        String filePath = config.getTestDataPath() + fileName;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row r = sheet.getRow(row);
            if (r == null) return "";
            Cell c = r.getCell(col);
            return getCellValue(c);
        } catch (IOException e) {
            log.error("Failed to read cell [{},{}] from {}", row, col, fileName, e);
            return "";
        }
    }
}
