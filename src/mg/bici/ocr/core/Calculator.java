/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.util.List;
import mg.bici.ocr.exception.GenericException;

/**
 *
 * @author Mickael
 */
public class Calculator {
        
    public static double[] convert(List<Double> numbers) throws GenericException {
        double[] numbersArray = new double[numbers.size()];
        for(int i = 0; i < numbersArray.length; i++) {
            numbersArray[i] = numbers.get(i);
        }
        return numbersArray;
    }
    
    // TODO: Changer pour supporter n'importe quel nombre
    // On suppose qu'on aura toujours 3 nombres (à généraliser)
    public static double getTotalPrice(double[] numbers) throws GenericException {
        if(numbers.length == 3) {
            if(numbers[0] * numbers[1] == numbers[2]) return numbers[2];
            else if(numbers[0] * numbers[2] == numbers[1]) return numbers[1];
            else return numbers[0];
        }
        else {
            throw new GenericException("Ne supporte que 3 nombres en ce moment");
        }
    }
    
    public static double getTotalPrice(List<Double> numbers) throws GenericException {
        return getTotalPrice(convert(numbers));
    }
}
