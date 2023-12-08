package dev.shingi.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.shingi.models.Customer;
import dev.shingi.models.ProgramState;
import dev.shingi.models.SnapshotCustomerList;
import javafx.collections.FXCollections;

public class SnapshotManager {

    public void createDuplicateSnapshot(ProgramState programManager, SnapshotCustomerList selectedSnapshot) {
        // Create a deep copy of the customers list
        List<Customer> copiedCustomers = new ArrayList<>();
        for (Customer customer : selectedSnapshot.getCustomers()) {
            // This assumes Customer class has a copy constructor or a similar method to create a copy
            copiedCustomers.add(customer.clone());
        }

        SnapshotCustomerList newCustomerListSnapshot = new SnapshotCustomerList(
        createDuplicatedSnapshotName(selectedSnapshot.getSnapshotName(), programManager.getAllSnapshotCustomerLists()),
        FXCollections.observableArrayList(copiedCustomers));

        programManager.getAllSnapshotCustomerLists().add(newCustomerListSnapshot);
        programManager.setCurrentCustomerListSnapshot(newCustomerListSnapshot);
        System.out.println("Succesfully created new snapshot.");
    }

    public String createDuplicatedSnapshotName(String originalSnapshotName, List<SnapshotCustomerList> allSnapshotNames) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDateStr = LocalDate.now().format(dateFormatter);

        // Regular expression to extract base name, date, and counter
        Pattern pattern = Pattern.compile("(.*?)( \\d{2}-\\d{2}-\\d{4})( - (\\d+))?$");
        Matcher matcher = pattern.matcher(originalSnapshotName);
        
        String baseName = matcher.matches() ? matcher.group(1) : originalSnapshotName;
        int maxCounter = 0;
        boolean dateExists = false;

        // Check existing snapshot names to find the max counter for today's date and if a snapshot exists with today's date
        for (int i = 0; i < allSnapshotNames.size(); i++) {
            String name = allSnapshotNames.get(i).getSnapshotName();
            matcher = pattern.matcher(name);
            if (matcher.matches() && matcher.group(1).equals(baseName) && matcher.group(2).trim().equals(currentDateStr)) {
                dateExists = true;
                String counterStr = matcher.group(4);
                int counter = counterStr != null ? Integer.parseInt(counterStr) : 0;
                maxCounter = Math.max(maxCounter, counter);
            }
        }

        // Form the new snapshot name
        String newSnapshotName = baseName + " " + currentDateStr;
        // Append an increment only if a snapshot with today's date already exists
        if (dateExists) {
            newSnapshotName += " - " + (maxCounter + 1);
        }

        return newSnapshotName;
    }
}
