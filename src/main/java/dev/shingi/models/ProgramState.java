package dev.shingi.models;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgramState {
    private static ProgramState instance = new ProgramState();

    private List<Runnable> currentCustomerListeners = new ArrayList<>();
    private List<Runnable> currentCustomerListSnapshotListeners = new ArrayList<>();

    private ObservableList<SnapshotCustomerList> allSnapshotCustomerLists;
    private SnapshotCustomerList currentCustomerListSnapshot;
    private Customer currentCustomer;

    private ProgramState() {
        currentCustomerListSnapshot = new SnapshotCustomerList("originalSnapshot", FXCollections.observableArrayList(SnapshotCustomerList.readCustomerData("src\\main\\resources\\klantendata.xlsx")));
        allSnapshotCustomerLists = FXCollections.observableArrayList(currentCustomerListSnapshot);
        currentCustomer = currentCustomerListSnapshot.getCustomers().get(0);
    }

    public static ProgramState getInstance() {
        return instance;
    }

    /*
     * Current customer listeners
     */
    public void addListenerCurrentCustomer(Runnable listener) {
        currentCustomerListeners.add(listener);
    }

    public void notifyAllCurrentCustomerListeners() {
        for (Runnable listener : currentCustomerListeners) {
            listener.run(); // Notify each listener of the change
        }
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        if (currentCustomerListSnapshot.getCustomers().contains(currentCustomer)) {
            this.currentCustomer = currentCustomerListSnapshot.getCustomers().get(currentCustomerListSnapshot.getCustomers().indexOf(currentCustomer));
            notifyAllCurrentCustomerListeners();
        }
    }

    /*
     * Current snapshot listeners
     */
    public void addListenerCurrentSnapshot(Runnable listener) {
        currentCustomerListSnapshotListeners.add(listener);
    }

    public void notifyAllCurrentCustomerListSnapshotListeners() {
        for (Runnable listener : currentCustomerListSnapshotListeners) {
            listener.run(); // Notify each listener of the change
        }
    }

    public SnapshotCustomerList getCurrentCustomerListSnapshot() {
        return currentCustomerListSnapshot;
    }

    public void setCurrentCustomerListSnapshot(SnapshotCustomerList currentCustomerListSnapshot) {
        this.currentCustomerListSnapshot = currentCustomerListSnapshot;
        notifyAllCurrentCustomerListSnapshotListeners();
    }

    /*
     * All snapshot listeners
     */
    public ObservableList<SnapshotCustomerList> getAllSnapshotCustomerLists() {
        return allSnapshotCustomerLists;
    }

    public void setAllSnapshotCustomerLists(ObservableList<SnapshotCustomerList> allSnapshotCustomerLists) {
        this.allSnapshotCustomerLists = allSnapshotCustomerLists;
    }
}
