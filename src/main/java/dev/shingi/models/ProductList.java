package dev.shingi.models;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ProductList implements Cloneable {

    private boolean administratieProduct1, administratieProduct2;
    private boolean belastingProduct1, belastingProduct2, belastingProduct3, belastingProduct4, belastingProduct5;
    private boolean controleProduct1, controleProduct2;
    private boolean salarisProduct1, salarisProduct2, salarisProduct3;
    private boolean jaarrekeningProduct1, jaarrekeningProduct2, jaarrekeningProduct3;
    private boolean customerSpecificProduct1, customerSpecificProduct2;
    private boolean customerSpecificProductAnders;
    private String customerSpecificProductAnders1, customerSpecificProductAnders2;

    @Override
    public ProductList clone() {
        try {
            return (ProductList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public ArrayList<String> getActiveProducts() {
        ArrayList<String> activeProducts = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Boolean waarde = (boolean) field.get(this);
                if (waarde) {
                    activeProducts.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                // Handle exception hier
                e.printStackTrace();
            }
        }
        
        return activeProducts;
    }

    public String getProductFieldByName(String productName) {
        try {
            Field field = this.getClass().getDeclaredField(productName);
            field.setAccessible(true);  // Maakt het veld toegankelijk als het privé is
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getProductByName(String productName) throws IllegalArgumentException, IllegalAccessException {
        try {
            Field field = this.getClass().getDeclaredField(productName);
            field.setAccessible(true);  // Maakt het veld toegankelijk als het privé is
            return (Boolean) field.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setProductStatus(String productName, boolean status) {
        try {
            Field field = this.getClass().getDeclaredField(productName);
            field.setAccessible(true);
            field.set(this, status);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean getAdministratieProduct1() {
        return administratieProduct1;
    }

    public void setAdministratieProduct1(boolean administratieProduct1) {
        this.administratieProduct1 = administratieProduct1;
    }

    public boolean getAdministratieProduct2() {
        return administratieProduct2;
    }

    public void setAdministratieProduct2(boolean administratieProduct2) {
        this.administratieProduct2 = administratieProduct2;
    }

    public boolean getBelastingProduct1() {
        return belastingProduct1;
    }

    public void setBelastingProduct1(boolean belastingProduct1) {
        this.belastingProduct1 = belastingProduct1;
    }

    public boolean getBelastingProduct2() {
        return belastingProduct2;
    }

    public void setBelastingProduct2(boolean belastingProduct2) {
        this.belastingProduct2 = belastingProduct2;
    }

    public boolean getBelastingProduct3() {
        return belastingProduct3;
    }

    public void setBelastingProduct3(boolean belastingProduct3) {
        this.belastingProduct3 = belastingProduct3;
    }

    public boolean getBelastingProduct4() {
        return belastingProduct4;
    }

    public void setBelastingProduct4(boolean belastingProduct4) {
        this.belastingProduct4 = belastingProduct4;
    }

    public boolean getBelastingProduct5() {
        return belastingProduct5;
    }

    public void setBelastingProduct5(boolean belastingProduct5) {
        this.belastingProduct5 = belastingProduct5;
    }

    public boolean getControleProduct1() {
        return controleProduct1;
    }

    public void setControleProduct1(boolean controleProduct1) {
        this.controleProduct1 = controleProduct1;
    }

    public boolean getControleProduct2() {
        return controleProduct2;
    }

    public void setControleProduct2(boolean controleProduct2) {
        this.controleProduct2 = controleProduct2;
    }

    public boolean getSalarisProduct1() {
        return salarisProduct1;
    }

    public void setSalarisProduct1(boolean salarisProduct1) {
        this.salarisProduct1 = salarisProduct1;
    }

    public boolean getSalarisProduct2() {
        return salarisProduct2;
    }

    public void setSalarisProduct2(boolean salarisProduct2) {
        this.salarisProduct2 = salarisProduct2;
    }

    public boolean getSalarisProduct3() {
        return salarisProduct3;
    }

    public void setSalarisProduct3(boolean salarisProduct3) {
        this.salarisProduct3 = salarisProduct3;
    }

    public boolean getJaarrekeningProduct1() {
        return jaarrekeningProduct1;
    }

    public void setJaarrekeningProduct1(boolean jaarrekeningProduct1) {
        this.jaarrekeningProduct1 = jaarrekeningProduct1;
    }

    public boolean getJaarrekeningProduct2() {
        return jaarrekeningProduct2;
    }

    public void setJaarrekeningProduct2(boolean jaarrekeningProduct2) {
        this.jaarrekeningProduct2 = jaarrekeningProduct2;
    }

    public boolean getJaarrekeningProduct3() {
        return jaarrekeningProduct3;
    }

    public void setJaarrekeningProduct3(boolean jaarrekeningProduct3) {
        this.jaarrekeningProduct3 = jaarrekeningProduct3;
    }

    public boolean getCustomerSpecificProduct1() {
        return customerSpecificProduct1;
    }

    public void setCustomerSpecificProduct1(boolean customerSpecificProduct1) {
        this.customerSpecificProduct1 = customerSpecificProduct1;
    }

    public boolean getCustomerSpecificProduct2() {
        return customerSpecificProduct2;
    }

    public void setCustomerSpecificProduct2(boolean customerSpecificProduct2) {
        this.customerSpecificProduct2 = customerSpecificProduct2;
    }

    public boolean getCustomerSpecificProductAnders() {
        return customerSpecificProductAnders;
    }

    public void setCustomerSpecificProductAnders(boolean customerSpecificProductAnders) {
        this.customerSpecificProductAnders = customerSpecificProductAnders;
    }

    public String getCustomerSpecificProductAnders1() {
        return customerSpecificProductAnders1;
    }

    public void setCustomerSpecificProductAnders1(String customerSpecificProductAnders1) {
        this.customerSpecificProductAnders1 = customerSpecificProductAnders1;
    }

    public String getCustomerSpecificProductAnders2() {
        return customerSpecificProductAnders2;
    }

    public void setCustomerSpecificProductAnders2(String customerSpecificProductAnders2) {
        this.customerSpecificProductAnders2 = customerSpecificProductAnders2;
    }
}