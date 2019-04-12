/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.main;

import java.util.HashMap;
import java.util.Map;
import mg.bici.ocr.core.Dictionary;
import mg.bici.ocr.core.Ocr;
import mg.bici.ocr.model.FactureFournisseur;
import org.json.simple.JSONObject;

/**
 *
 * @author Mickael
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Ocr ocr = new Ocr();
        JSONObject json = ocr.getJSONObject("success/2.pdf");
        Map<String, String> mapping = new HashMap();
        mapping.put(Dictionary.BILL_NUMBER_LABEL, "numFact");
        mapping.put(Dictionary.ISSUE_DATE_LABEL, "dateEmission");
        mapping.put(Dictionary.DUE_DATE_LABEL, "datyecheance");
        FactureFournisseur facture = (FactureFournisseur) ocr.getHeader(json, FactureFournisseur.class, mapping);
    }
}
