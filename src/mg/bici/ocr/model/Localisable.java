/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;


/**
 *
 * @author Mickael
 */
public class Localisable {
    private WordPosition wordPosition;
    private Localisable prev;
    private Localisable next;

    public WordPosition getWordPosition() {
        return wordPosition;
    }

    public void setWordPosition(WordPosition wordPosition) {
        this.wordPosition = wordPosition;
    }

    public Localisable getPrev() {
        return prev;
    }

    public void setPrev(Localisable prev) {
        this.prev = prev;
    }

    public Localisable getNext() {
        return next;
    }

    public void setNext(Localisable next) {
        this.next = next;
    }

}
