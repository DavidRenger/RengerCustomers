package dev.shingi.controllers;

import dev.shingi.models.Customer;
import dev.shingi.models.ProgramState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CustomerDetailsController {

    // Constants
    private static final String[] STATUSSEN = { "Actief", "Gestopt" };
    private static final String[] RECHTSVORMEN = { "B.V.", "Stichting", "Eenmanszaak zonder werknemer(s)", "Eenmanszaak met werknemer(s)", "Kerkgenootschap", "V.O.F.", "Anders" };
    private static final String[] BOEKHOUDPAKKETTEN = { "Snelstart", "Twinfield", "Exact Online", "Eboekhouden", "Rompslomp" };

    // Program state observer
    private ProgramState programManager;
    private Customer currentCustomer;

    // Other configuration fields
    @FXML private ComboBox<String> statusComboBox;
    @FXML private ComboBox<String> rechtsvormComboBox;
    @FXML private ComboBox<String> boekhoudpakketComboBox;
    @FXML private TextField hoofdpersoonTextField, contactpersoonTextField;
    @FXML private TextArea aantekeningenTextField;

    // Product configuration
    @FXML private CheckBox administratieCheckBox1, administratieCheckBox2, belastingCheckBox1, belastingCheckBox2, belastingCheckBox3, belastingCheckBox4, belastingCheckBox5;
    @FXML private CheckBox controleCheckBox1, controleCheckBox2;
    @FXML private CheckBox salarisCheckBox1, salarisCheckBox2, salarisCheckBox3;
    @FXML private CheckBox jaarrekeningCheckBox1, jaarrekeningCheckBox2, jaarrekeningCheckBox3;
    @FXML private CheckBox customerSpecificCheckBox1, customerSpecificCheckBox2, customerSpecificCheckBoxAnders;
    @FXML private TextField customerSpecificTextFieldAnders1, customerSpecificTextFieldAnders2;


    public void initialize() {
        // Get current program instance
        this.programManager = ProgramState.getInstance();
        this.programManager.addListenerCurrentCustomer(this::updateView);
        this.currentCustomer = programManager.getCurrentCustomer();

        // Initialize the comboboxes by setting their lists
        initializeComboBoxes();
    }

    private void initializeComboBoxes() {
        ObservableList<String> statussenList = FXCollections.observableArrayList(STATUSSEN);
        statusComboBox.setItems(statussenList);

        ObservableList<String> rechtsvormenList = FXCollections.observableArrayList(RECHTSVORMEN);
        rechtsvormComboBox.setItems(rechtsvormenList);

        ObservableList<String> boekhoudpakkettenList = FXCollections.observableArrayList(BOEKHOUDPAKKETTEN);
        boekhoudpakketComboBox.setItems(boekhoudpakkettenList);
    }

    // Update the view when a new current customer is selected
    public void updateView() {
        if (programManager.getCurrentCustomer() != null) {
            // Get current customer
            currentCustomer = programManager.getCurrentCustomer();

            // Set products
            administratieCheckBox1.setSelected(currentCustomer.getProductList().getAdministratieProduct1());
            administratieCheckBox2.setSelected(currentCustomer.getProductList().getAdministratieProduct2());

            belastingCheckBox1.setSelected(currentCustomer.getProductList().getBelastingProduct1());
            belastingCheckBox2.setSelected(currentCustomer.getProductList().getBelastingProduct2());
            belastingCheckBox3.setSelected(currentCustomer.getProductList().getBelastingProduct3()); 
            belastingCheckBox4.setSelected(currentCustomer.getProductList().getBelastingProduct4());
            belastingCheckBox5.setSelected(currentCustomer.getProductList().getBelastingProduct5());

            controleCheckBox1.setSelected(currentCustomer.getProductList().getControleProduct1());
            controleCheckBox2.setSelected(currentCustomer.getProductList().getControleProduct2());

            salarisCheckBox1.setSelected(currentCustomer.getProductList().getSalarisProduct1()); 
            salarisCheckBox2.setSelected(currentCustomer.getProductList().getSalarisProduct2());
            salarisCheckBox3.setSelected(currentCustomer.getProductList().getSalarisProduct3());

            jaarrekeningCheckBox1.setSelected(currentCustomer.getProductList().getJaarrekeningProduct1());
            jaarrekeningCheckBox2.setSelected(currentCustomer.getProductList().getJaarrekeningProduct2());
            jaarrekeningCheckBox3.setSelected(currentCustomer.getProductList().getJaarrekeningProduct3());

            customerSpecificCheckBox1.setSelected(currentCustomer.getProductList().getCustomerSpecificProduct1()); 
            customerSpecificCheckBox2.setSelected(currentCustomer.getProductList().getCustomerSpecificProduct2());
            customerSpecificCheckBoxAnders.setSelected(currentCustomer.getProductList().getCustomerSpecificProductAnders());
            customerSpecificTextFieldAnders1.setText(currentCustomer.getProductList().getCustomerSpecificProductAnders1());
            customerSpecificTextFieldAnders2.setText(currentCustomer.getProductList().getCustomerSpecificProductAnders2());

            // Set other configuration fields
            statusComboBox.getSelectionModel().select(currentCustomer.getStatus());
            rechtsvormComboBox.getSelectionModel().select(currentCustomer.getRechtsvorm());;
            boekhoudpakketComboBox.getSelectionModel().select(currentCustomer.getBoekhoudpakket());;
            hoofdpersoonTextField.setText(currentCustomer.getHoofdpersoon());
            contactpersoonTextField.setText(currentCustomer.getContactpersoon());;
            aantekeningenTextField.setText(currentCustomer.getAantekeningen());;
        }
    }
    
    /*
     * Set the field in the customer object whenever its respective component changes in the gui
     */
    // Basic info fields
    @FXML private void handleStatusComboBox(ActionEvent event) { currentCustomer.setStatus(statusComboBox.getSelectionModel().getSelectedItem()); }
    @FXML private void handleRechtsvormComboBox(ActionEvent event) { currentCustomer.setRechtsvorm(rechtsvormComboBox.getSelectionModel().getSelectedItem()); }
    @FXML private void handleBoekhoudpakketComboBox(ActionEvent event) { currentCustomer.setBoekhoudpakket(boekhoudpakketComboBox.getSelectionModel().getSelectedItem()); }
    @FXML private void handleHoofdpersoonTextField(KeyEvent keyEvent) { currentCustomer.setHoofdpersoon(hoofdpersoonTextField.getText()); }
    @FXML private void handleContactpersoonTextField(KeyEvent keyEvent) { currentCustomer.setContactpersoon(contactpersoonTextField.getText()); }
    @FXML private void handleAantekeningenTextArea(KeyEvent keyEvent) { currentCustomer.setAantekeningen(aantekeningenTextField.getText()); }
    
    // Product fields in the customer's product list
    @FXML private void handleAdministratieProduct1(ActionEvent event) { currentCustomer.getProductList().setAdministratieProduct1(administratieCheckBox1.isSelected()); }
    @FXML private void handleAdministratieProduct2(ActionEvent event) { currentCustomer.getProductList().setAdministratieProduct2(administratieCheckBox2.isSelected()); }

    @FXML private void handleBelastingProduct1(ActionEvent event) { currentCustomer.getProductList().setBelastingProduct1(belastingCheckBox1.isSelected()); }
    @FXML private void handleBelastingProduct2(ActionEvent event) { currentCustomer.getProductList().setBelastingProduct2(belastingCheckBox2.isSelected()); }
    @FXML private void handleBelastingProduct3(ActionEvent event) { currentCustomer.getProductList().setBelastingProduct3(belastingCheckBox3.isSelected()); }
    @FXML private void handleBelastingProduct4(ActionEvent event) { currentCustomer.getProductList().setBelastingProduct4(belastingCheckBox4.isSelected()); }
    @FXML private void handleBelastingProduct5(ActionEvent event) { currentCustomer.getProductList().setBelastingProduct5(belastingCheckBox5.isSelected()); }

    @FXML private void handleControleProduct1(ActionEvent event) { currentCustomer.getProductList().setControleProduct1(controleCheckBox1.isSelected()); }
    @FXML private void handleControleProduct2(ActionEvent event) { currentCustomer.getProductList().setControleProduct2(controleCheckBox2.isSelected()); }
    
    @FXML private void handleSalarisProduct1(ActionEvent event) { currentCustomer.getProductList().setSalarisProduct1(salarisCheckBox1.isSelected()); }
    @FXML private void handleSalarisProduct2(ActionEvent event) { currentCustomer.getProductList().setSalarisProduct2(salarisCheckBox2.isSelected()); }
    @FXML private void handleSalarisProduct3(ActionEvent event) { currentCustomer.getProductList().setSalarisProduct3(salarisCheckBox3.isSelected()); }

    @FXML private void handleJaarrekeningProduct1(ActionEvent event) { currentCustomer.getProductList().setJaarrekeningProduct1(jaarrekeningCheckBox1.isSelected()); }
    @FXML private void handleJaarrekeningProduct2(ActionEvent event) { currentCustomer.getProductList().setJaarrekeningProduct2(jaarrekeningCheckBox2.isSelected()); }
    @FXML private void handleJaarrekeningProduct3(ActionEvent event) { currentCustomer.getProductList().setJaarrekeningProduct3(jaarrekeningCheckBox3.isSelected()); }

    @FXML private void handleCustomerSpecificProduct1(ActionEvent event) { currentCustomer.getProductList().setCustomerSpecificProduct1(customerSpecificCheckBox1.isSelected()); }
    @FXML private void handleCustomerSpecificProduct2(ActionEvent event) { currentCustomer.getProductList().setCustomerSpecificProduct2(customerSpecificCheckBox2.isSelected()); }

    @FXML private void handleCustomerSpecificProductAnders(ActionEvent event) {
        currentCustomer.getProductList().setCustomerSpecificProductAnders(customerSpecificCheckBoxAnders.isSelected()); 
        customerSpecificTextFieldAnders1.setDisable(!customerSpecificCheckBoxAnders.isSelected());
        customerSpecificTextFieldAnders2.setDisable(!customerSpecificCheckBoxAnders.isSelected());
    }

    @FXML private void handleCustomerSpecificTextFieldAnders1(KeyEvent keyEvent) { currentCustomer.getProductList().setCustomerSpecificProductAnders1(customerSpecificTextFieldAnders1.getText()); }
    @FXML private void handleCustomerSpecificTextFieldAnders2(KeyEvent keyEvent) { currentCustomer.getProductList().setCustomerSpecificProductAnders2(customerSpecificTextFieldAnders2.getText()); }
}
