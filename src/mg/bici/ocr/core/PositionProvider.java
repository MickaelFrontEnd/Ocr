/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.util.ArrayList;
import java.util.List;
import mg.bici.ocr.exception.GenericException;
import mg.bici.ocr.model.WordPosition;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mickael
 */
public class PositionProvider {
    private Element element;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
    
    public PositionProvider(){}
    
    public PositionProvider(Element element) {
        this.setElement(element);
    }
    
    public Elements getElement(String[] words) {
        Elements elements;
        for(int i = 0; i < words.length; i++){
            elements = getElement().select(String.format("span:contains(%s)",words[i]));
            if(elements != null) return elements;
        }
        return null;
    }
    
    // textContainingPosition a pour format bbox 1292 1060 1377 1132; x_wconf 63
    public static WordPosition getWordPosition(String textContainingPosition) throws GenericException {
        String bbox = textContainingPosition.split(";")[0];
        bbox = bbox.replace("bbox ", "");
        String[] positions = bbox.split(" ");
        if(positions.length == 4) {
            return new WordPosition(positions[0],positions[1],positions[2],positions[3]);
        }
        else {
            throw new GenericException("Position invalide");
        }
    }
    
    private boolean containsWords(Element element, String[] words) {
        for(String word:words){
            if(element.text().toLowerCase().contains(word.toLowerCase())) return true;
        }
        return false;
    }
    
    public List<WordPosition> getPosition(String word) throws Exception {
        Elements elements = getElement().select(String.format("span:containsOwn(%s)", word));
        List<WordPosition> result = new ArrayList();
        for(Element element:elements) {
            result.add(getWordPosition(element.attr("title")));
        }
        return result;
    }
    
    public List<WordPosition> getPosition(String[] words) throws Exception {
        for(String word:words) {
            if ((getPosition(word) != null && (!getPosition(word).isEmpty()))) {
                return getPosition(word);
            }
        }
        return null;
    }
    
    public WordPosition getFirstPosition(String word) throws Exception {
        List<WordPosition> positions = getPosition(word);
        if(positions != null && positions.size() > 0) return positions.get(0);
        return null;
    }
    
    public WordPosition getFirstPosition(String[] words) throws Exception {
        List<WordPosition> result =  getPosition(words);
        if(result != null && !result.isEmpty()) return result.get(0);
        return null;
    }
    
    public static WordPosition getPosition(Element element) throws GenericException {
        if(element.hasAttr("title")) {
            return getWordPosition(element.attr("title"));
        }
        return null;
    }
    
    public WordPosition getQuantityPosition() throws Exception {
        return getFirstPosition(Dictionnary.getQuantity());
    }
    
    // TODO: Optimisation code
    public WordPosition getUnitPricePosition() throws Exception {
        WordPosition result = getFirstPosition(Dictionnary.getUnitPrice()[0]);
        if(result != null) return result; // Dans le cas où le header contient directement pu
        Elements elements = getElement(Dictionnary.getPrice());
        Element unit = null;
        Element ht = null;
        // A OPTIMISER
        for(Element element:elements) {
            unit = element.nextElementSibling();
            if(containsWords(unit,Dictionnary.getUnit())) {
                ht = unit.nextElementSibling();
                if(containsWords(ht,Dictionnary.getHt())) {
                    WordPosition pricePosition = getPosition(element);
                    WordPosition htPosition = getPosition(ht);
                    pricePosition.setX2(htPosition.getX2());
                    return pricePosition;
                }
                else {
                    WordPosition pricePosition = getPosition(element);
                    WordPosition unitPosition = getPosition(unit);
                    pricePosition.setX2(unitPosition.getX2());
                    return pricePosition;
                }               
            }
        }
        return null;
    }
    
    // TODO: Optimisation code
    public WordPosition getTotalPricePosition() throws Exception {
        Elements elements = getElement(Dictionnary.getPrice());
        Element total = null;
        Element ht = null;
        // A OPTIMISER
        for(Element element:elements) {
            total = element.nextElementSibling();
            if(containsWords(total,Dictionnary.getTotal())) {
                ht = total.nextElementSibling();
                if(containsWords(ht,Dictionnary.getHt())) {
                    WordPosition pricePosition = getPosition(element);
                    WordPosition htPosition = getPosition(ht);
                    pricePosition.setX2(htPosition.getX2());
                    return pricePosition;
                }
                else {
                    WordPosition pricePosition = getPosition(element);
                    WordPosition unitPosition = getPosition(total);
                    pricePosition.setX2(unitPosition.getX2());
                    return pricePosition;
                }               
            }
        }
        WordPosition result = getFirstPosition(Dictionnary.getTotal());
        return result;
    }
    
    public WordPosition getTvaPosition() throws Exception {
        return getFirstPosition(Dictionnary.getTva());
    }
    
    public WordPosition getDesignationPosition() throws Exception {
        return getFirstPosition(Dictionnary.getDesignation());
    }
}
