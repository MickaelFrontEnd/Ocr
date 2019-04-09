/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import java.util.ArrayList;
import java.util.List;
import mg.bici.ocr.exception.GenericException;

/**
 *
 * @author Mickael
 */
public class LocalisableNumber extends Localisable {
    private double number;
    
    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
    
    public void setNumber(String number) throws GenericException {
        try {
            this.number = Double.parseDouble(number);
        }
        catch(NumberFormatException ex) {
            throw new GenericException("Nombre invalide");
        }
    }
    
    public LocalisableNumber(double number,WordPosition wordPosition) {
        this.setNumber(number);
        this.setWordPosition(wordPosition);
    }
    
    public LocalisableNumber(String number,WordPosition wordPosition) throws GenericException {
        this.setNumber(number);
        this.setWordPosition(wordPosition);
    }
    
    public static List<Double> getNumberOnly(List<LocalisableNumber> numbers) {
        List<Double> result = new ArrayList();
        for(LocalisableNumber number:numbers) {
            result.add(number.getNumber());
        }
        return result;
    }

}
