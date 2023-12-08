package dev.shingi.utils;

import java.util.LinkedHashMap;

public class MappingUtils {

    // Method mapping "ProductName" to "Header Name in klant databestand"
    public static LinkedHashMap<String, String> getProductNamesToHeaderNamesMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Aangifte Inkomstenbelasting", "Heeft product Aangifte Inkomstenbelasting");
        map.put("Aangifte Loonbelasting", "Heeft product Aangifte Loonbelasting");
        map.put("Aangifte Omzetbelasting", "Heeft product Aangifte Omzetbelasting");
        map.put("Aangifte Vennootschapsbelasting", "Heeft product Aangifte Vennootschapsbelasting");
        map.put("Aangifte Dividendbelasting", "Heeft product Aangifte Dividendbelasting");
        map.put("Jaarrekening", "Heeft product Jaarrekening");
        map.put("Salarisadministratie", "Heeft product Salarisadministratie");
        map.put("Financiële administratie", "Heeft product Financiële administratie");
        map.put("Kwartaal controle Omzetbelasting", "Heeft product Kwartaal controle Omzetbelasting");
        map.put("Financieel jaaroverzicht bank", "Heeft product Financieel jaaroverzicht bank");
        map.put("Jaarcontrole BTW en jaarafsluiting", "Heeft product Jaarcontrole BTW en jaarafsluiting");
        map.put("Incassobatch Children Asking", "Heeft product Incassobatch Children Asking");
        map.put("Vrijwilligersvergoeding Credohuis", "Heeft product Vrijwilligersvergoeding Credohuis");
        map.put("Salarisbatches klaarzetten", "Heeft product Salarisbatches klaarzetten");
        map.put("Loonheffing batches klaarzetten", "Heeft product Loonheffing batches klaarzetten");
        map.put("Notulen AVA", "Heeft product Notulen AVA");
        map.put("Deponering jaarrekening KvK", "Heeft product Deponering jaarrekening KvK");
        return map;
    }

    // Method mapping "ProductName" to "FieldNames in klas Productenlijst"
    public static LinkedHashMap<String, String> getProductNamesToFieldNamesMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Aangifte Inkomstenbelasting", "belastingProduct2");
        map.put("Aangifte Loonbelasting", "belastingProduct3");
        map.put("Aangifte Omzetbelasting", "belastingProduct4");
        map.put("Aangifte Vennootschapsbelasting", "belastingProduct5");
        map.put("Aangifte Dividendbelasting", "belastingProduct1");
        map.put("Jaarrekening", "jaarrekeningProduct3");
        map.put("Salarisadministratie", "salarisProduct2");
        map.put("Financiële administratie", "administratieProduct2");
        map.put("Kwartaal controle Omzetbelasting", "controleProduct2");
        map.put("Financieel jaaroverzicht bank", "administratieProduct1");
        map.put("Jaarcontrole BTW en jaarafsluiting", "controleProduct1");
        map.put("Incassobatch Children Asking", "customerSpecificProduct1");
        map.put("Vrijwilligersvergoeding Credohuis", "customerSpecificProduct2");
        map.put("Salarisbatches klaarzetten", "salarisProduct3");
        map.put("Loonheffing batches klaarzetten", "salarisProduct1");
        map.put("Notulen AVA", "jaarrekeningProduct2");
        map.put("Deponering jaarrekening KvK", "jaarrekeningProduct1");
        return map;
    }

    // Method mapping "Header Name in klant databestand" to "FieldNames in klas Productenlijst"
    public static LinkedHashMap<String, String> getAllHeaderNamesToFieldNamesMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Name", "name");
        map.put("Actief/Gestopt", "status");
        map.put("Boekhoudpakket", "boekhoudpakket");
        map.put("Hoofdpersoon", "hoofdpersoon");
        map.put("Contactpersoon", "contactpersoon");
        map.put("Rechtsvorm / Pakket", "rechtsvorm");
        map.put("Heeft product Aangifte Inkomstenbelasting", "belastingProduct2");
        map.put("Heeft product Aangifte Loonbelasting", "belastingProduct3");
        map.put("Heeft product Aangifte Omzetbelasting", "belastingProduct4");
        map.put("Heeft product Aangifte Vennootschapsbelasting", "belastingProduct5");
        map.put("Heeft product Aangifte Dividendbelasting", "belastingProduct1");
        map.put("Heeft product Jaarrekening", "jaarrekeningProduct3");
        map.put("Heeft product Salarisadministratie", "salarisProduct2");
        map.put("Heeft product Financiële administratie", "administratieProduct2");
        map.put("Heeft product Kwartaal controle Omzetbelasting", "controleProduct2");
        map.put("Heeft product Financieel jaaroverzicht bank", "administratieProduct1");
        map.put("Heeft product Jaarcontrole BTW en jaarafsluiting", "controleProduct1");
        map.put("Heeft product Incassobatch Children Asking", "customerSpecificProduct1");
        map.put("Heeft product Vrijwilligersvergoeding Credohuis", "customerSpecificProduct2");
        map.put("Heeft product Salarisbatches klaarzetten", "salarisProduct3");
        map.put("Heeft product Loonheffing batches klaarzetten", "salarisProduct1");
        map.put("Heeft product Notulen AVA", "jaarrekeningProduct2");
        map.put("Heeft product Deponering jaarrekening KvK", "jaarrekeningProduct1");
        return map;
    }
}
