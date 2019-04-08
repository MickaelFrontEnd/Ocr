/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import java.util.List;

/**
 *
 * @author Mickael
 */
public class LocalisableWord extends Localisable {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public LocalisableWord(String word,WordPosition wordPosition) {
        this.setWord(word);
        this.setWordPosition(wordPosition);
    }

    // TODO: Généraliser dans la classe mère
    public LocalisableNumber getNearest(List<LocalisableNumber> localisables) {
        if (this.getWordPosition() == null) {
            return null;
        }
        LocalisableNumber result = null;
        int gap = Integer.MAX_VALUE;
        for (LocalisableNumber localisable : localisables) {
            if (!(localisable.getWordPosition().getX2() < this.getWordPosition().getX1())) {
                if (localisable.getWordPosition().getX2() - this.getWordPosition().getX2() <= gap) {
                    gap = localisable.getWordPosition().getX2() - this.getWordPosition().getX2();
                    result = localisable;
                }
            }
        }
        return result;
    }
}
