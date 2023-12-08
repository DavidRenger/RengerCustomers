package dev.shingi.models;

import java.io.IOException;
import java.util.ArrayList;

import dev.shingi.utils.ExcelReadUtils;
import dev.shingi.utils.ExcelWriteUtils;
import javafx.collections.ObservableList;

public class SnapshotCustomerList {

    private String snapshotName;
    private ObservableList<Customer> customers;

    public SnapshotCustomerList(String snapshotName, ObservableList<Customer> customers) {
        this.snapshotName = snapshotName;
        this.customers = customers;
    }

    public void writeCustomerData(ObservableList<Customer> customers, String fileOutputPath) throws IOException {
        ExcelWriteUtils.writeCustomerDataToExcelFile(customers, fileOutputPath);
    }

    public static ArrayList<Customer> readCustomerData(String bestandspad) {
        return ExcelReadUtils.readCustomerData(bestandspad);
    }

    @Override
    public String toString() {
        return snapshotName;
    }

    public String getSnapshotName() {
        return snapshotName;
    }

    public void setSnapshotName(String snapshotName) {
        this.snapshotName = snapshotName;
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ObservableList<Customer> customers) {
        this.customers = customers;
    }
}