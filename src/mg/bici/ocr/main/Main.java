/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.main;

import java.io.File;
import mg.bici.ocr.core.Ocr;

/**
 *
 * @author Mickael
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Ocr ocr = new Ocr();
        System.out.println(ocr.getCore().getTesseract().doOCR(new File("success/modele-facture-freelance.pdf")));
        System.out.println(ocr.getJSON("success/modele-facture-freelance.pdf"));
    }
}
