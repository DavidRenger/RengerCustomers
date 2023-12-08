package dev.shingi.controllers;

import java.util.ArrayList;
import java.util.List;
import dev.shingi.models.Customer;
import dev.shingi.models.ProgramState;
import dev.shingi.utils.AlertUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class CustomerListController {

    private ProgramState programManager;

    // List view
    @FXML private ListView<Customer> customersListView;

    // Filter toggles
    @FXML private ToggleGroup filterGroup;
    @FXML private RadioButton filterAllCustomers, filterUncheckedCustomers, filterCheckedCustomers;

    // List navigation and progress
    @FXML private Button previousCustomerButton, nextCustomerButton;
    @FXML private ProgressBar progressBar;

    private ObservableList<Customer> currentCustomers;
    private final ChangeListener<Boolean> customerCheckedListener = (obs, oldValue, newValue) -> updateListView();

    public void initialize() {
        programManager = ProgramState.getInstance();
        programManager.addListenerCurrentSnapshot(this::updateView);
        currentCustomers = FXCollections.observableArrayList(programManager.getCurrentCustomerListSnapshot().getCustomers());

        initializeFilters();
        initializeListView();
        updateProgressBarBinding();
    }

    private void initializeListView() {
        attachCheckedListenersToCustomers();
        updateListView(); // Initially populate the ListView

        // Add a listener to the customer list view
        customersListView.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldValue, newValue) -> {
                if (newValue != null) {
                    programManager.setCurrentCustomer(newValue);
                }
            }
        );
    }

    private void initializeFilters() {
        filterGroup = new ToggleGroup();
        filterUncheckedCustomers.setToggleGroup(filterGroup);
        filterCheckedCustomers.setToggleGroup(filterGroup);
        filterAllCustomers.setToggleGroup(filterGroup);

        filterGroup.selectToggle(filterUncheckedCustomers);
        filterGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> updateListView());
    }

    private void attachCheckedListenersToCustomers() {
        for (Customer customer : currentCustomers) {
            customer.checkedProperty().removeListener(customerCheckedListener);
            customer.checkedProperty().addListener(customerCheckedListener);
        }
    }

    private void updateView() {
        currentCustomers = FXCollections.observableArrayList(programManager.getCurrentCustomerListSnapshot().getCustomers());
        attachCheckedListenersToCustomers();
        updateListView();
        updateProgressBarBinding();
        customersListView.getSelectionModel().selectFirst();
    }

    private void updateProgressBarBinding() {
        progressBar.progressProperty().unbind();
        List<ObservableValue<?>> dependencies = new ArrayList<>();
        for (Customer customer : currentCustomers) {
            dependencies.add(customer.checkedProperty());
        }
        progressBar.progressProperty().bind(Bindings.createDoubleBinding(() ->
            calculateProgress(), dependencies.toArray(new ObservableValue[0])));
    }
    
    private double calculateProgress() {
        long checkedCount = currentCustomers.stream().filter(Customer::isChecked).count();
        double progress = currentCustomers.isEmpty() ? 0 : (double) checkedCount / currentCustomers.size();
        if (progress == 1) {
            AlertUtils.showInformationDialog("Fantastisch!", "Alle klanten zijn verwerkt.", "Helemaal toppie, goed gedaan! Vergeet niet de data te exporteren =)");
        }
        return progress;
    }
    

    private void updateListView() {
        Toggle selectedToggle = filterGroup.getSelectedToggle();
        ObservableList<Customer> displayedCustomers = FXCollections.observableArrayList();

        for (Customer customer : currentCustomers) {
            if (selectedToggle == filterUncheckedCustomers && !customer.isChecked()) {
                displayedCustomers.add(customer);
            } else if (selectedToggle == filterCheckedCustomers && customer.isChecked()) {
                displayedCustomers.add(customer);
            } else if (selectedToggle == filterAllCustomers) {
                displayedCustomers.add(customer);
            }
        }

        customersListView.setItems(displayedCustomers);
    }

    @FXML
    public void handleNextCustomerButton(ActionEvent event) {
        if (!programManager.getCurrentCustomerListSnapshot().getSnapshotName().equals("originalSnapshot")) {
            if (customersListView.getSelectionModel().getSelectedIndex() < currentCustomers.size() - 1) { // Check if not the last customer
                Customer currentCustomer = customersListView.getSelectionModel().getSelectedItem();
                if (currentCustomer != null) {
                    currentCustomer.setChecked(true);
                    if (filterGroup.getSelectedToggle() == filterUncheckedCustomers) {
                        customersListView.getSelectionModel().selectFirst();
                    } else {
                        customersListView.getSelectionModel().selectNext();
                    }
                    programManager.setCurrentCustomer(customersListView.getSelectionModel().getSelectedItem());
                }
            }
        }
    }

    @FXML
    public void handlePreviousCustomerButton(ActionEvent event) {
        int previousIndex = customersListView.getSelectionModel().getSelectedIndex() - 1;
        if (previousIndex >= 0) {
            customersListView.getSelectionModel().select(previousIndex);
            programManager.setCurrentCustomer(currentCustomers.get(previousIndex));
        }
    }
}
