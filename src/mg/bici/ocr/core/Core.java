/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sourceforge.tess4j.Tesseract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mickael
 */
public class Core {
    
    private Tesseract tesseract;
    private String tessdataPath;
    private String language;
    private static final String TESSDATA_EMBEDDED = "tessdata";
    private int pageSegMode;
    private boolean preserveSpace;
    private int tableBreakerNumber;

    public int getTableBreakerNumber() {
        return tableBreakerNumber;
    }

    public void setTableBreakerNumber(int tableBreakerNumber) {
        this.tableBreakerNumber = tableBreakerNumber;
    }

    public boolean isPreserveSpace() {
        return preserveSpace;
    }

    public void setPreserveSpace(boolean preserveSpace) {
        this.preserveSpace = preserveSpace;
    }

    public int getPageSegMode() {
        return pageSegMode;
    }

    public void setPageSegMode(int pageSegMode) {
        this.pageSegMode = pageSegMode;
    }

    public String getTessdataPath() {
        return tessdataPath;
    }

    public void setTessdataPath(String tessdataPath) {
        this.tessdataPath = tessdataPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Tesseract getTesseract() {
        return tesseract;
    }

    public void setTesseract(Tesseract tesseract) {
        this.tesseract = tesseract;
    }
    
    public Core(){
        this.tesseract = new Tesseract();
        this.setTessdataPath(TESSDATA_EMBEDDED);
        this.setLanguage("fra");
        this.setPageSegMode(1);
        this.setPreserveSpace(true);
        this.setTableBreakerNumber(2);
        this.initTesseractConfig();
    }
    
    public Core(String tessdataPath){
        this.tesseract = new Tesseract();
        this.setTessdataPath(tessdataPath);
        this.setLanguage("fra");
        this.initTesseractConfig();
    }
    
    public Core(String tessdataPath, String language){
        this.tesseract = new Tesseract();
        this.setTessdataPath(tessdataPath);
        this.setLanguage(language);
        this.initTesseractConfig();
    }
    
    private void initTesseractConfig(){
        this.tesseract.setDatapath(getTessdataPath());
        this.tesseract.setLanguage(getLanguage());
        this.tesseract.setPageSegMode(getPageSegMode());
        if(this.isPreserveSpace()) this.tesseract.setTessVariable("preserve_interword_spaces", "true");
    }
    
    public String generateHtml(String path) throws Exception {
        File file = new File(path);
        this.getTesseract().setHocr(true);
        return this.getTesseract().doOCR(file);
    }
    
    public Document generateDocument(String path) throws Exception {
        return Jsoup.parse(generateHtml(path));
    }
    
    public Element getElement(Document document,String[] dictionnaries) {
        Elements elements = null;
        for(int i = 0; i < dictionnaries.length; i++){
            elements = document.select("span:contains(" + dictionnaries[i] + ")");
            if(elements != null && elements.size() > 1) return elements.get(1);
        }
        return null;
    }
    
    public Element getDesignation(Document document) {
        return getElement(document,Dictionnary.DESIGNATION);
    }
    
    public Element getTableHeader(Document document) {
        return getDesignation(document).parent();
    }
    
    public int getNumberOfNumber(Element element) {
        int counter = 0;
        Elements childrens = element.children();
        if(!childrens.isEmpty()) {
            for(Element children:childrens) {  
                try {
                    Double.parseDouble(children.text());
                    counter ++;
                }
                catch(NumberFormatException ex) {}
            }
        }
        return counter;
    }
    
    public boolean containsNumberMoreThan(Element element, int number) {
        return getNumberOfNumber(element) >= number;
    }
    
        
    
    public List<Element> getTableRows(Document document) {
        Element tableHeader = getTableHeader(document);
        List<Element> result = new ArrayList<>();
        if(tableHeader != null) {
            Elements rows = tableHeader.children();
            for(Element row : rows) {
                if(containsNumberMoreThan(row,getTableBreakerNumber())) result.add(row);
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        Core core = new Core();
        Document document = core.generateDocument("modele-de-facture.pdf");
        Element element = core.getTableHeader(document);
        System.out.println(element);
    }
}
