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
    
    public static final String QUANTITY_LABEL = "Quantité";
    public static final String UNIT_PRICE_LABEL = "Prix unitaire";
    public static final String TOTAL_PRICE_LABEL = "Prix total";
    public static final String TVA_LABEL = "Tva";
    public static final String DESIGNATION_LABEL = "Désignation";
    
    // TODOS: Les valeurs suivantes doivent êtres lues à partir d'un fichier
    
    public static String[] getDesignation() {
        return new String[] {"désignation","designation"};
    }
    
    public static String[] getPrice() {
        return new String[] {"prix","price"};
    }
    
    public static String[] getUnit() {
        return new String[] {"unitaire","unit"};
    }
    
    public static String[] getUnitPrice() {
        return new String[] {"pu"};
    }
    
    public static String[] getTotal() {
        return new String[] {"total"};
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
}
