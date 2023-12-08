package dev.shingi.controllers;

import dev.shingi.models.ProgramState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PrimaryController {

    private ProgramState programManager;
    
    @FXML private HBox contentHBox;
    @FXML private Label customerNameLabel;

    public void initialize() {
        // Get the current instance of the program
        programManager = ProgramState.getInstance();
        programManager.addListenerCurrentCustomer(this::updateCustomerLabel);
        programManager.addListenerCurrentSnapshot(this::updateContentHBox);
        updateContentHBox();
    }

    private void updateCustomerLabel() {
        customerNameLabel.setText(programManager.getCurrentCustomer().getName());
    }

    private void updateContentHBox() {
        if (programManager.getCurrentCustomerListSnapshot().getSnapshotName().equals("originalSnapshot")) {
            contentHBox.setDisable(true);
        } else {
            contentHBox.setDisable(false);
        }
    }
}
