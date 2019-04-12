/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;
import mg.bici.ocr.exception.GenericException;
import mg.bici.ocr.model.LocalisableWord;
import mg.bici.ocr.model.WordPosition;
import org.json.simple.JSONObject;
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

    public static Object convert(JSONObject json, Class classe, Map<String, String> mapping) throws Exception {
        try {
            Object result = classe.newInstance();
            Method getter;
            Method setter;
            String fieldName;
            for (Map.Entry<String, String> entry : mapping.entrySet()) {
                fieldName = upperFirstLetter(entry.getValue());
                getter = classe.getMethod("get" + fieldName);
                setter = classe.getMethod("set" + fieldName, getter.getReturnType());
                if (getter.getReturnType() == double.class || getter.getReturnType() == Double.class) {
                    setter.invoke(result, (double) json.get(entry.getKey()));
                } else if (getter.getReturnType() == int.class || getter.getReturnType() == Integer.class) {
                    setter.invoke(result, (int) json.get(entry.getKey()));
                } else if (getter.getReturnType() == java.sql.Date.class) {
                    setter.invoke(result, convertDate((String) json.get(entry.getKey())));
                } else {
                    setter.invoke(result, (String) json.get(entry.getKey()));
                }
            }
            return result;
        } catch (InstantiationException ex) {
            throw new Exception("Problème d'instanciation de l'objet");
        }
    }

    public static java.sql.Date convertDate(String date) throws Exception {
        StringProvider stringProvider = new StringProvider(date);
        stringProvider.setPlainText(stringProvider.clearDate());
        SimpleDateFormat sdf = new SimpleDateFormat(stringProvider.getDateFormat());
        return new java.sql.Date(sdf.parse(date).getTime());
    }

    public static String upperFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1, text.length());
    }
}
