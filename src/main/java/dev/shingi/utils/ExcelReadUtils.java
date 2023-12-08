package dev.shingi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dev.shingi.models.Customer;
import dev.shingi.models.ProductList;

public class ExcelReadUtils {
    public static ArrayList<Customer> readCustomerData(String bestandspad) {
        ArrayList<Customer> customers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(bestandspad));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            LinkedHashMap<Integer, String> headerMap = createHeaderMap(headerRow);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Customer customer = processRow(row, headerMap);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    private static LinkedHashMap<Integer, String> createHeaderMap(Row headerRow) {
        LinkedHashMap<Integer, String> headerMap = new LinkedHashMap<>();
        headerRow.forEach(cell -> {
            if (cell != null) {
                headerMap.put(cell.getColumnIndex(), cell.getStringCellValue());
            }
        });
        return headerMap;
    }

    private static Customer processRow(Row row, LinkedHashMap<Integer, String> headerMap) {
        Cell customerNameCell = row.getCell(0);
        if (customerNameCell == null || customerNameCell.getCellType() == CellType.BLANK) {
            return null; // skip rows with empty first cell
        }
        
        Customer customer = new Customer(customerNameCell.getStringCellValue());
        ProductList productList = new ProductList();
        
        readCustomerDataFromRow(row, headerMap, customer, productList);
        customer.setProductList(productList);

        return customer;
    }

    private static void readCustomerDataFromRow(Row row, LinkedHashMap<Integer, String> headerMap, Customer customer, ProductList productList) {
        for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                String headerName = headerMap.get(cellIndex);
                populateFieldFromCell(cell, headerName, customer, productList);
            }
        }
    }

    private static void populateFieldFromCell(Cell cell, String headerName, Customer customer, ProductList productList) {
        String fieldName = MappingUtils.getAllHeaderNamesToFieldNamesMap().get(headerName);
        if (fieldName != null) {
            try {
                if (isFieldInClass(fieldName, ProductList.class)) {
                    setFieldValue(cell, fieldName, productList);
                } else {
                    setFieldValue(cell, fieldName, customer);
                }
            } catch (ReflectiveOperationException e) {
                // Handle exception or log it
                e.printStackTrace();
            }
        }
    }

    private static boolean isFieldInClass(String fieldName, Class<?> clazz) {
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static void setFieldValue(Cell cell, String fieldName, Object targetObject) throws ReflectiveOperationException {
        Field field = targetObject.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        
        // Check if the field is of type boolean
        if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            String cellValue = cell.getStringCellValue().trim().toLowerCase();
            boolean booleanValue = "ja".equals(cellValue); // "ja" becomes true, anything else becomes false
            field.set(targetObject, booleanValue);
        } else {
            // Assume it's a string for this example
            field.set(targetObject, cell.getStringCellValue());
        }
    }    
}
