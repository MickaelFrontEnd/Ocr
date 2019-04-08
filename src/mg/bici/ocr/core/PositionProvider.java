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
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

/**
 *
 * @author Mickael
 */
public class PositionProvider {
    private Document document;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    public PositionProvider(Document document) {
        this.setDocument(document);
    }
    
    // textContainingPosition correspond to bbox 1292 1060 1377 1132; x_wconf 63
    public WordPosition getWordPosition(String textContainingPosition) throws GenericException {
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
    
    public List<WordPosition> getPosition(String word) throws Exception {
        Elements elements = document.select(String.format("span:containsOwn(%s)", word));
        List<WordPosition> result = new ArrayList();
        for(Element element:elements) {
            result.add(getWordPosition(element.attr("title")));
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        Core core = new Core();
        Document document = core.generateDocument("modele-de-facture.pdf");
        PositionProvider positionProvider = new PositionProvider(document);
        List<WordPosition> positions = positionProvider.getPosition("m2");
        for(WordPosition position:positions) {
            System.out.println(position);
        }
    }
}
