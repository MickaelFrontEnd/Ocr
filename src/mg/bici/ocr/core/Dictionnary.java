/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

/**
 *
 * @author Mickael
 */
public class Dictionnary {
    
    public static final String QUANTITY_LABEL = "quantite";
    public static final String UNIT_PRICE_LABEL = "prix_unitaire";
    public static final String TOTAL_PRICE_LABEL = "prix_total";
    public static final String TVA_LABEL = "tva";
    public static final String DESIGNATION_LABEL = "designation";
    public static final String DISCOUNT_LABEL = "remise";
    
    // TODO: Les valeurs suivantes doivent êtres lues à partir d'un fichier
    
    public static String[] getDesignation() {
        return new String[]{"désignation", "designation", "déscription", "description"};
    }
    
    public static String[] getPrice() {
        return new String[]{"prix", "montant", "price"};
    }
    
    public static String[] getUnit() {
        return new String[] {"unitaire","unit"};
    }
    
    public static String[] getUnitPrice() {
        return new String[] {"pu"};
    }
    
    public static String[] getTotal() {
        return new String[]{"total", "montant"};
    }
    
    public static String[] getQuantity() {
        return new String[] {"quantité","quantite","qté","qte","qt"};
    }
    
    public static String[] getHt() {
        return new String[] {"ht"};
    }
    
    public static String[] getTva() {
        return new String[] {"tva"};
    }

    public static String[] getRate() {
        return new String[]{"taux", "rate"};
    }

    public static String[] getTvaWordSeparator() {
        return new String[]{"de"};
    }
}
