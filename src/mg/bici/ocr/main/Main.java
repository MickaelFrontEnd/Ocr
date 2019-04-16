/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mg.bici.ocr.core.Dictionary;
import mg.bici.ocr.core.Ocr;
import mg.bici.ocr.model.DetailFacture;
import mg.bici.ocr.model.FactureFournisseur;
import org.json.simple.JSONObject;

/**
 *
 * @author Mickael
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Ocr ocr = new Ocr();

        // Exemple en donnant le path du fichier pdf
        // On peut aussi donner un File en tant que entrée
        JSONObject json = ocr.getJSONObject("failed/20.pdf");

        // Exemple de mapping entete
        Map<String, String> mappingHeader = new HashMap();
        mappingHeader.put(Dictionary.BILL_NUMBER_LABEL, "numFact");
        mappingHeader.put(Dictionary.ISSUE_DATE_LABEL, "dateEmission");
        mappingHeader.put(Dictionary.DUE_DATE_LABEL, "datyecheance");

        // Exemple de mapping corps
        Map<String, String> mappingBody = new HashMap();
        mappingBody.put(Dictionary.QUANTITY_LABEL, "qte");
        mappingBody.put(Dictionary.UNIT_PRICE_LABEL, "pubHT");
        mappingBody.put(Dictionary.DESIGNATION_LABEL, "designation");

        // Exemple pour avoir l'en tête
        FactureFournisseur header = (FactureFournisseur) ocr.getHeader(json, FactureFournisseur.class, mappingHeader);
        System.out.println(String.format("Numéro facture: %s", header.getNumFact()));
        System.out.println(String.format("Date emission: %s", header.getDateEmission()));
        System.out.println(String.format("Date d'echeance: %s", header.getDatyecheance()));
        System.out.println();

        // Exemple pour avoir toute les lignes
        List<Object> rows = ocr.getBody(json, DetailFacture.class, mappingBody);
        DetailFacture facture;

        for (Object row : rows) {
            facture = (DetailFacture) row;
            System.out.println(String.format("Désignation: %s", facture.getDesignation()));
            System.out.println(String.format("Quantité: %.2f", facture.getQte()));
            System.out.println(String.format("Prix unitaire: %.2f", facture.getPubHT()));
            System.out.println();
        }
    }
}
