/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import mg.bici.ocr.exception.GenericException;
import mg.bici.ocr.model.WordPosition;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mickael
 */
public class DomManipulator {
    private Element element;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public DomManipulator(Element element) {
        this.setElement(element);
    }

    public Element getNext() {
        return this.getElement().nextElementSibling();
    }

    public Element getPrev() {
        return this.getElement().previousElementSibling();
    }

    public Element getElement(String[] dictionnaries) {
        Elements elements;
        for (int i = 0; i < dictionnaries.length; i++) {
            elements = getElement().select(String.format("span:contains(%s)", dictionnaries[i]));
            if (elements != null && elements.size() > 1) {
                return elements.get(1);
            }
        }
        return null;
    }

    public Elements getElements(String[] words) {
        Elements elements;
        for (int i = 0; i < words.length; i++) {
            elements = getElement().select(String.format("span:contains(%s)", words[i]));
            if (elements != null) {
                return elements;
            }
        }
        return null;
    }

    public Element getDesignation(Document document) {
        return getElement(Dictionnary.getDesignation());
    }

    public Element getTableHeader(Document document) {
        return getDesignation(document).parent();
    }

    public Elements getNextSiblings() {
        Elements elements = new Elements();
        Element tmp = getElement().nextElementSibling();
        while (tmp != null) {
            elements.add(tmp);
            tmp = tmp.nextElementSibling();
        }
        return elements;
    }

    public Elements getElements(int begin, int end) throws GenericException {
        Elements result = new Elements();
        Elements elements = getElement().children();
        WordPosition temp = null;
        for (Element element : elements) {
            temp = PositionProvider.getPosition(element);
            if (begin <= temp.getX1() && temp.getX2() <= end) {
                result.add(element);
            }
        }
        return result;
    }

}
