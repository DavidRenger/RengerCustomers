package dev.shingi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dev.shingi.models.Customer;
import javafx.collections.ObservableList;

public class ExcelWriteUtils {

    // TO DO: Kolommen voor verwerkt en aantekeningen toevoegen.
    public static void writeCustomerDataToExcelFile(ObservableList<Customer> customers, String fileOutputPath) throws IOException {
        // Create new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customers");

        // Create header row
        createHeaderRow(sheet);

        // Row index, start after headerrow
        int rowIndex = 1; 

        // Write customer data to sheet
        for (Customer customer : customers) {
            Row row = sheet.createRow(rowIndex);
            rowIndex++;

            // Write basic customer info
            row.createCell(0).setCellValue(customer.isChecked());
            row.createCell(1).setCellValue(customer.getName());
            row.createCell(2).setCellValue(customer.getStatus());
            row.createCell(3).setCellValue(customer.getBoekhoudpakket());
            row.createCell(4).setCellValue(customer.getHoofdpersoon());
            row.createCell(5).setCellValue(customer.getContactpersoon());
            row.createCell(6).setCellValue(customer.getRechtsvorm());
            row.createCell(7).setCellValue(customer.getAantekeningen());
            
            // Write product info
            row.createCell(8).setCellValue(customer.getProductList().getBelastingProduct2());
            row.createCell(9).setCellValue(customer.getProductList().getBelastingProduct3());
            row.createCell(10).setCellValue(customer.getProductList().getBelastingProduct4());
            row.createCell(11).setCellValue(customer.getProductList().getBelastingProduct5());
            row.createCell(12).setCellValue(customer.getProductList().getBelastingProduct1());
            row.createCell(13).setCellValue(customer.getProductList().getJaarrekeningProduct3());
            row.createCell(14).setCellValue(customer.getProductList().getSalarisProduct2());
            row.createCell(15).setCellValue(customer.getProductList().getAdministratieProduct2());
            row.createCell(16).setCellValue(customer.getProductList().getControleProduct2());
            row.createCell(17).setCellValue(customer.getProductList().getAdministratieProduct1());
            row.createCell(18).setCellValue(customer.getProductList().getControleProduct1());
            row.createCell(19).setCellValue(customer.getProductList().getCustomerSpecificProduct1());
            row.createCell(20).setCellValue(customer.getProductList().getCustomerSpecificProduct2());
            row.createCell(21).setCellValue(customer.getProductList().getSalarisProduct3());
            row.createCell(22).setCellValue(customer.getProductList().getSalarisProduct1());
            row.createCell(23).setCellValue(customer.getProductList().getJaarrekeningProduct2());
            row.createCell(24).setCellValue(customer.getProductList().getJaarrekeningProduct1());
            row.createCell(25).setCellValue(customer.getProductList().getCustomerSpecificProductAnders1());
            row.createCell(26).setCellValue(customer.getProductList().getCustomerSpecificProductAnders2());
        }

        // Write out to file
        try (FileOutputStream fileOut = new FileOutputStream(new File(fileOutputPath + "/CustomerData_" + LocalDate.now() + ".xlsx"))) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        workbook.close();
    }

    // Maak de headerrij
    private static Row createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        Map<String, Integer> headerIndexMap = new HashMap<>();

        // Create 'verwerkt' header
        headerRow.createCell(0).setCellValue("Verwerkt");
        headerIndexMap.put("Verwerkt", 0);

        int i = 1;
        for (Map.Entry<String, String> entry : MappingUtils.getAllHeaderNamesToFieldNamesMap().entrySet()) {
            if (i == 7) {
                // Create 'verwerkt' header
                headerRow.createCell(i).setCellValue("Aantekeningen");
                headerIndexMap.put("Aantekening", i);
                i++;
            }
            headerRow.createCell(i).setCellValue(entry.getKey());
            headerIndexMap.put(entry.getKey(), i);
            i++;
        }
        // Create 'customer specific other' headers
            headerRow.createCell(i++).setCellValue("Klant-specifiek anders 1");
            headerRow.createCell(i).setCellValue("Klant-specifiek anders 2");

        return headerRow;
    }
}
