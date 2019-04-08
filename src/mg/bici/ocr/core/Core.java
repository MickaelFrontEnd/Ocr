/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.io.File;
import java.util.ArrayList;
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
    private String tessdataPath = "tessdata";
    private String language = "fra";
    private int pageSegMode = 1;
    private boolean preserveSpace = true;
    private int tableBreakerNumber = 2;

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
        Elements elements;
        for(int i = 0; i < dictionnaries.length; i++){
            elements = document.select("span:contains(" + dictionnaries[i] + ")");
            if(elements != null && elements.size() > 1) return elements.get(1);
        }
        return null;
    }
    
    public Element getDesignation(Document document) {
        return getElement(document,Dictionnary.getDesignation());
    }
    
    public Element getTableHeader(Document document) {
        return getDesignation(document).parent();
    }
    
    public List<Double> extractNumber(Element element) {
        List<Double> result = new ArrayList();
        Elements childrens = element.children();
        if(!childrens.isEmpty()) {
            for(Element children:childrens) {  
                try {
                    result.add(Double.parseDouble(children.text()));
                }
                catch(NumberFormatException ex) {}
            }
        }
        return result;
    }
    
    public int getNumberOfNumber(Element element) {
        return extractNumber(element).size();
    }
    
    public boolean containsNumberMoreThan(Element element, int number) {
        return getNumberOfNumber(element) >= number;
    }
      
    public List<Element> getTableRows(Document document) {
        Element tableHeader = getTableHeader(document);
        List<Element> result = new ArrayList<>();
        if(tableHeader != null) {
            Elements rows = tableHeader.siblingElements();
            for(Element row : rows) {
                if(containsNumberMoreThan(row,getTableBreakerNumber())) result.add(row);
            }
        }
        return result;
    }
    
    /*public double getTotalPrice(Element element) {
        
    }*/
    
    public static void main(String[] args) throws Exception {
        Core core = new Core();
        Document document = core.generateDocument("modele-de-facture.pdf");
        List<Element> rows = core.getTableRows(document);
        for(Element row:rows) {
            List<Double> numbers = core.extractNumber(row);
            for(double number:numbers) {
                System.out.println(number);               
            }
        }
    }
}
