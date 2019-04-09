/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import mg.bici.ocr.exception.GenericException;
import mg.bici.ocr.model.LocalisableWord;
import mg.bici.ocr.model.WordPosition;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mickael
 */
public class Converter {
    public static LocalisableWord convertToLocalisableWord(Element element) throws GenericException {
        LocalisableWord result = new LocalisableWord();
        WordPosition wordPosition = PositionProvider.getWordPosition(element.attr("title"));
        result.setWord(element.text());
        result.setWordPosition(wordPosition);
        return result;
    }

    public static String convertToString(Elements elements) {
        StringBuilder stringBuilder = new StringBuilder();
        elements.stream().map((element) -> {
            stringBuilder.append(element.text());
            return element;
        }).forEachOrdered((_item) -> {
            stringBuilder.append(" ");
        });
        return stringBuilder.toString();
    }
}
