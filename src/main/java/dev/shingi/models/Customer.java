package dev.shingi.models;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Customer implements Cloneable {

    private ProductList productList;

    private String name;
    private String boekhoudpakket;
    private String hoofdpersoon;
    private String contactpersoon;
    private String rechtsvorm;
    private String aantekeningen;
    private String status; // Is klant actief of gestopt?
    private BooleanProperty checked = new SimpleBooleanProperty(); // Is de 'Volgende' knop tenminste 1 keer voor deze klant geklikt?

    public Customer(String name) {
        this.name = name;
        this.checked.setValue(false);
        this.productList = new ProductList();
    }

    @Override
    public Customer clone() {
        try {
            Customer cloned = (Customer) super.clone();

            // Deep copy for the ProductList object
            if (this.productList != null) {
                cloned.productList = this.productList.clone();
            }

            // Deep copy for BooleanProperty
            cloned.checked = new SimpleBooleanProperty(this.checked.get());

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String toStringAllData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Klant: ").append(name).append("\n");
        sb.append("Boekhoudpakket: '").append(boekhoudpakket).append("', ");
        sb.append("Hoofdpersoon: '").append(hoofdpersoon).append("', ");
        sb.append("Contactpersoon: '").append(contactpersoon).append("', ");
        sb.append("Rechtsvorm: '").append(rechtsvorm).append("', ");
        sb.append("Aantekeningen: '").append(aantekeningen).append("', ");
        sb.append("Status: ").append(status).append(", ");
        sb.append("Verwerkt: ").append(checked.get() ? "Ja" : "Nee");
        sb.append("\n");

        // Voeg actieve producten toe
        List<String> activeProducts = productList.getActiveProducts();
        sb.append("Actieve Producten: \n");
        for (int i = 0; i < activeProducts.size(); i++) {
                sb.append(activeProducts.get(i));
            if (i != activeProducts.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }


    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoekhoudpakket() {
        return boekhoudpakket;
    }

    public void setBoekhoudpakket(String boekhoudpakket) {
        this.boekhoudpakket = boekhoudpakket;
    }

    public String getHoofdpersoon() {
        return hoofdpersoon;
    }

    public void setHoofdpersoon(String hoofdpersoon) {
        this.hoofdpersoon = hoofdpersoon;
    }

    public String getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public String getRechtsvorm() {
        return rechtsvorm;
    }

    public void setRechtsvorm(String rechtsvorm) {
        this.rechtsvorm = rechtsvorm;
    }

    public String getAantekeningen() {
        return aantekeningen;
    }

    public void setAantekeningen(String aantekeningen) {
        this.aantekeningen = aantekeningen;
    }

    public boolean isChecked() {
        return checked.get();
    }

    public void setChecked(boolean checked) {
        this.checked.setValue(checked);
        System.out.println("Updated checked for " + name + " to " + checked);
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
