package dev.shingi.controllers;

import dev.shingi.models.ProgramState;
import dev.shingi.models.SnapshotCustomerList;
import dev.shingi.services.SnapshotManager;
import dev.shingi.utils.AlertUtils;

import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SnapshotCustomerListController {
    
    private SnapshotManager snapshotManager;
    private ProgramState programManager;
    
    // Snapshots list view and respective buttons
    @FXML private ListView<SnapshotCustomerList> customerDataSnapshotsListView;
    @FXML private Button deleteCustomerDataButton, createDuplicateCustomerDataButton;

    // Exporting utility fields
    @FXML private Button setFileLocationButton, exportCustomerDataButton;
    @FXML private TextField fileLocationTextField;

    private String fileOutputPath;
    
    public void initialize() {
        this.snapshotManager = new SnapshotManager();
        this.programManager = ProgramState.getInstance();
        this.customerDataSnapshotsListView.setItems(programManager.getAllSnapshotCustomerLists());
        this.customerDataSnapshotsListView.getSelectionModel().select(0);
        
        customerDataSnapshotsListView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<SnapshotCustomerList>() {
                @Override
                public void changed(ObservableValue<? extends SnapshotCustomerList> observable, SnapshotCustomerList oldValue, SnapshotCustomerList newValue) {
                    programManager.setCurrentCustomerListSnapshot(newValue);
                }
            }
        );
    }

    @FXML
    public void handleDuplicateButton(ActionEvent event) {
        this.snapshotManager.createDuplicateSnapshot(programManager, customerDataSnapshotsListView.getSelectionModel().getSelectedItem());
        this.customerDataSnapshotsListView.getSelectionModel().select(programManager.getCurrentCustomerListSnapshot());
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {
        if (customerDataSnapshotsListView.getSelectionModel().getSelectedIndex() != 0) {
            if (AlertUtils.showConfirmationDialog("Verwijderen", "Weet je zeker dat je wilt verwijderen?", "Klik op OK om door te gaan.")) {
                programManager.getAllSnapshotCustomerLists().remove(customerDataSnapshotsListView.getSelectionModel().getSelectedIndex());
                System.out.println("Succesfully removes snapshot.");
            }
        } else {
            System.out.println("Cannot remove original snapshot.");
        }
    }

    @FXML
    public void handleSetFileOutputLocation(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Kies map voor de export");

        // Get the current stage from the event source
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Show the directory chooser dialog
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Set the selected directory's path as the file output path
            fileOutputPath = selectedDirectory.getAbsolutePath();
            fileLocationTextField.setText(fileOutputPath);
        }
    }

    @FXML
    public void handleFileOutputLocationTextField(KeyEvent keyEvent) {
        fileOutputPath = fileLocationTextField.getText();
    }

    @FXML
    public void handleCustomerDataExport() {
        if (fileOutputPath != null) {
            if (!fileOutputPath.isEmpty()) {
                try {
                    programManager.getCurrentCustomerListSnapshot().writeCustomerData(programManager.getCurrentCustomerListSnapshot().getCustomers(), fileOutputPath);
                    AlertUtils.showInformationDialog("Succes!", "De klantendata is geexporteerd naar: " + fileOutputPath, "Klik op OK om door te gaan.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AlertUtils.showInformationDialog("Onmogelijk", "Selecteer eerst een locatie om het bestand op te slaan.", "Klik op OK om door te gaan.");
        }
    }
}
