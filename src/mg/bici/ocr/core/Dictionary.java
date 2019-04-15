/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import mg.bici.ocr.config.ConfigurationAccessor;
import mg.bici.ocr.config.FileConfigurationAccessor;

/**
 *
 * @author Mickael
 */
public class Dictionary {

    private static final ConfigurationAccessor CONFIG = new FileConfigurationAccessor();

    public static final String QUANTITY_LABEL = "quantite";
    public static final String UNIT_PRICE_LABEL = "prix_unitaire";
    public static final String TOTAL_PRICE_LABEL = "prix_total";
    public static final String TVA_LABEL = "tva";
    public static final String DESIGNATION_LABEL = "designation";
    public static final String DISCOUNT_LABEL = "remise";
    public static final String PRICE_LABEL = "prix";
    public static final String UNIT_LABEL = "unitaire";
    public static final String TOTAL_LABEL = "total";
    public static final String HT_LABEL = "ht";
    public static final String RATE_LABEL = "taux";
    public static final String TVA_SEPARATOR_LABEL = "separateur_tva";
    public static final String BILL_NUMBER_LABEL = "numero_facture";
    public static final String ISSUE_DATE_LABEL = "date_emission";
    public static final String DUE_DATE_LABEL = "date_echeance";
    public static final String HEADER = "en_tete";
    public static final String BODY = "corps";
    public static final String MONETARY_UNIT_LABEL = "unite";
    
    public static String[] getDesignation() {
        return CONFIG.getDictionary(DESIGNATION_LABEL);
    }
    
    public static String[] getPrice() {
        return CONFIG.getDictionary(PRICE_LABEL);
    }
    
    public static String[] getUnit() {
        return CONFIG.getDictionary(UNIT_LABEL);
    }
    
    public static String[] getUnitPrice() {
        return CONFIG.getDictionary(UNIT_PRICE_LABEL);
    }
    
    public static String[] getTotal() {
        return CONFIG.getDictionary(TOTAL_LABEL);
    }
    
    public static String[] getQuantity() {
        return CONFIG.getDictionary(QUANTITY_LABEL);
    }
    
    public static String[] getHt() {
        return CONFIG.getDictionary(HT_LABEL);
    }
    
    public static String[] getTva() {
        return CONFIG.getDictionary(TVA_LABEL);
    }

    public static String[] getRate() {
        return CONFIG.getDictionary(RATE_LABEL);
    }

    public static String[] getTvaWordSeparator() {
        return CONFIG.getDictionary(TVA_SEPARATOR_LABEL);
    }

    public static String[] getBillNumber() {
        return CONFIG.getDictionary(BILL_NUMBER_LABEL);
    }

    public static String[] getIssueDate() {
        return CONFIG.getDictionary(ISSUE_DATE_LABEL);
    }

    public static String[] getDueDate() {
        return CONFIG.getDictionary(DUE_DATE_LABEL);
    }

    public static String[] getMonetaryUnit() {
        return CONFIG.getDictionary(MONETARY_UNIT_LABEL);
    }
}
