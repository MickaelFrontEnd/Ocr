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
        for (Element elem : elements) {
            result.add(getWordPosition(elem.attr("title")));
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
        return getFirstPosition(Dictionary.getQuantity());
    }
    
    // TODO: Optimisation code
    public WordPosition getUnitPricePosition() throws Exception {
        WordPosition result = getFirstPosition(Dictionary.getUnitPrice()[0]);
        if (result != null) {
            return result; // Dans le cas où le header contient directement pu
        }
        DomManipulator domManipulator = new DomManipulator(getElement());
        Elements elements = domManipulator.getElements(Dictionary.getPrice());
        Element unit;
        Element ht;
        // A OPTIMISER
        for (Element elem : elements) {
            unit = elem.nextElementSibling();
            if(containsWords(unit,Dictionary.getUnit())) {
                ht = unit.nextElementSibling();
                if(containsWords(ht,Dictionary.getHt())) {
                    WordPosition pricePosition = getPosition(elem);
                    WordPosition htPosition = getPosition(ht);
                    pricePosition.setX2(htPosition.getX2());
                    return pricePosition;
                }
                else {
                    WordPosition pricePosition = getPosition(elem);
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
        DomManipulator domManipulator = new DomManipulator(getElement());
        Elements elements = domManipulator.getElements(Dictionary.getPrice());
        Element total;
        Element ht;
        // A OPTIMISER
        for (Element elem : elements) {
            total = elem.nextElementSibling();
            if(containsWords(total,Dictionary.getTotal())) {
                ht = total.nextElementSibling();
                if(containsWords(ht,Dictionary.getHt())) {
                    WordPosition pricePosition = getPosition(elem);
                    WordPosition htPosition = getPosition(ht);
                    pricePosition.setX2(htPosition.getX2());
                    return pricePosition;
                }
                else {
                    WordPosition pricePosition = getPosition(elem);
                    WordPosition unitPosition = getPosition(total);
                    pricePosition.setX2(unitPosition.getX2());
                    return pricePosition;
                }               
            }
        }
        WordPosition result = getFirstPosition(Dictionary.getTotal());
        return result;
    }
    
    public WordPosition getTvaPosition() throws Exception {
        return getFirstPosition(Dictionary.getTva());
    }
    
    public WordPosition getDesignationPosition() throws Exception {
        return getFirstPosition(Dictionary.getDesignation());
    }
}
