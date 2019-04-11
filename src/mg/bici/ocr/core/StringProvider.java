/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mickael
 */
public class StringProvider {
    private String plainText;

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public StringProvider(String plainText) {
        this.setPlainText(plainText);
    }

    public String getPattern(String[] texts) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String text : texts) {
            stringBuilder.append("(");
            stringBuilder.append(text);
            stringBuilder.append(")|");
        }
        String result = stringBuilder.toString();
        return result.substring(0, result.lastIndexOf("|"));
    }

    // TODO: Optimiser le code
    public String[] splitByLine() {
        return getPlainText().split("\\n", -1);
    }

    // TODO: Optimiser le code
    public String getPartialString(String[] texts) {
        Pattern pattern = Pattern.compile(getPattern(texts));
        String[] lines = splitByLine();
        for (String line : lines) {
            if (pattern.matcher(line.toLowerCase()).find()) {
                return line;
            }
        }
        return "";
    }

    // TODO: Optimiser le code
    public String getString(String template) {
        Pattern pattern = Pattern.compile(template);
        Matcher matcher = pattern.matcher(getPlainText());
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
    // TODO: Optimiser le code
    public String clear(String text) {
        String result = text;
        try {
            if (text.contains(":")) {
                result = text.substring(text.lastIndexOf(":") + 1);
            }
            if (result.contains("°")) {
                result = text.substring(text.lastIndexOf("°") + 1);
            }
            return result;
        } catch (Exception ex) {
            return result;
        }
    }

    // TODO: Optimiser le code
    public String getBillNumber() {
        String line = getPartialString(Dictionary.getBillNumber());
        String[] words = line.split(" ", -1);
        String temp;
        for (String word : words) {
            temp = clear(word);
            if (temp.matches(".*\\d.*")) {
                return temp;
            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        Ocr ocr = new Ocr();
        StringProvider stringProvider = new StringProvider(ocr.getCore().generatePlainText("success/modele-facture-freelance.pdf"));
        stringProvider.setPlainText("facturen°2018-022");
        System.out.println(stringProvider.getBillNumber());
    }
}
