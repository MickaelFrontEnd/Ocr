/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mg.bici.ocr.exception.GenericException;
import mg.bici.ocr.model.LocalisableNumber;
import mg.bici.ocr.model.LocalisableWord;
import mg.bici.ocr.model.Table;
import mg.bici.ocr.model.TableHeader;
import mg.bici.ocr.model.TableRow;
import mg.bici.ocr.model.WordPosition;
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
    private int pageSegMode = 6;
    private boolean preserveSpace = false;
    private int tableBreakerNumber = 2;
    private PositionProvider positionProvider;

    public PositionProvider getPositionProvider() {
        return positionProvider;
    }

    public void setPositionProvider(PositionProvider positionProvider) {
        this.positionProvider = positionProvider;
    }
    
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

    public String generatePlainText(String path) throws Exception {
        File file = new File(path);
        this.getTesseract().setHocr(false);
        return this.getTesseract().doOCR(file);
    }
    
    public Document generateDocument(String path) throws Exception {
        return Jsoup.parse(generateHtml(path));
    }

    // TODO: Doit supporter les entrées de type 20%,30$,100£,......
    public List<LocalisableNumber> extractNumber(Element element) {
        List<LocalisableNumber> result = new ArrayList();
        Elements childrens = element.children();
        if(!childrens.isEmpty()) {
            childrens.forEach((children) -> {
                try {
                    result.add(new LocalisableNumber(
                            children.text(),
                            PositionProvider.getPosition(children)
                    ));
                }
                catch(GenericException ex) {}
            });
        }
        return result;
    }
    
    public int getNumberOfNumber(Element element) throws GenericException {
        return extractNumber(element).size();
    }
    
    public boolean containsNumberMoreThan(Element element, int number) throws GenericException {
        return getNumberOfNumber(element) > number;
    }

    private LocalisableWord getDesignation(Element element) throws GenericException {
        DomManipulator domManipulator = new DomManipulator(element);
        Element designation = domManipulator.getElement(Dictionary.getDesignation());
        if (designation != null) {
            LocalisableWord result = Converter.convertToLocalisableWord(designation);
            domManipulator.setElement(designation);
            Element prev = domManipulator.getPrev();
            Element next = domManipulator.getNext();
            if (prev != null) {
                result.setPrev(Converter.convertToLocalisableWord(prev));
            }
            if (next != null) {
                result.setNext(Converter.convertToLocalisableWord(next));
            }
            return result;
        }
        return null;
    }

    // TODO: Refactoriser code
    public LocalisableWord getDesignation(TableHeader tableHeader, Element row) throws GenericException {
        int begin = tableHeader.getDesignation().getPrev() != null
                ? tableHeader.getDesignation().getPrev().getWordPosition().getX2()
                : -1;
        int end = tableHeader.getDesignation().getNext() != null
                ? tableHeader.getDesignation().getNext().getWordPosition().getX1()
                : Integer.MAX_VALUE;
        DomManipulator domManipulator = new DomManipulator(row);
        Elements elements = domManipulator.getElements(begin, end);
        LocalisableWord result = new LocalisableWord();
        result.setWord(Converter.convertToString(elements));
        return result;
    }

    // TODO: Trouver un moyen plus flexible de stopper la recherche des lignes
    public List<Element> getTableRows(Document document) throws GenericException {
        DomManipulator domManipulator = new DomManipulator(document);
        Element tableHeader = domManipulator.getTableHeader(document);
        List<Element> result = new ArrayList<>();
        if (tableHeader != null) {
            domManipulator.setElement(tableHeader);
            Elements rows = domManipulator.getNextSiblings();
            for(Element row : rows) {
                if (containsNumberMoreThan(row, getTableBreakerNumber())) {
                    result.add(row);
                } else {
                    return result;
                }
            }
        }
        return result;
    }
    
    public double getTotalPrice(List<LocalisableNumber> numbers) throws GenericException {
        return Calculator.getTotalPrice(LocalisableNumber.getNumberOnly(numbers));
    }
    
    public double getTotalPrice(Element element) throws GenericException {
        return getTotalPrice(extractNumber(element));
    }
    
    public TableHeader constructHeader(Document document) throws Exception {
        DomManipulator domManipulator = new DomManipulator(document);
        Element tableHeader = domManipulator.getTableHeader(document);
        PositionProvider pr = new PositionProvider(tableHeader);
        
        WordPosition quantityPosition = pr.getQuantityPosition();
        WordPosition unitPricePosition = pr.getUnitPricePosition();
        WordPosition totalPricePosition = pr.getTotalPricePosition();
        WordPosition tvaPosition = pr.getTvaPosition();
        
        LocalisableWord quantity = new LocalisableWord(Dictionary.QUANTITY_LABEL, quantityPosition);
        LocalisableWord unitPrice = new LocalisableWord(Dictionary.UNIT_PRICE_LABEL, unitPricePosition);
        LocalisableWord totalPrice = new LocalisableWord(Dictionary.TOTAL_PRICE_LABEL, totalPricePosition);
        LocalisableWord tva = new LocalisableWord(Dictionary.TVA_LABEL, tvaPosition);
        LocalisableWord designation = getDesignation(tableHeader);
        
        return new TableHeader(quantity,designation,unitPrice,totalPrice,tva);
    }

    // TODOS: Inclure toute les colonnes possibles
    public List<TableRow> constructRows(TableHeader tableHeader, Document document) throws Exception {
        List<Element> rows = getTableRows(document);
        List<LocalisableNumber> numbers;
        List<TableRow> result = new ArrayList();
        TableRow tableRow;
        for (Element row : rows) {
            numbers = extractNumber(row);
            tableRow = new TableRow();
            tableRow.setQuantity(tableHeader.getQuantity().getNearest(numbers));
            tableRow.setUnitPrice(tableHeader.getUnitPrice().getNearest(numbers));
            tableRow.setTotalPrice(tableHeader.getTotalPrice().getNearest(numbers));
            tableRow.setDesignation(getDesignation(tableHeader, row));
            if (tableHeader.getTva() != null) {
                tableRow.setTva(tableHeader.getTva().getNearest(numbers));
            }
            result.add(tableRow);
        }
        return result;
    }

    public List<TableRow> constructRows(Document document) throws Exception {
        return constructRows(constructHeader(document), document);
    }

    public Table constructTable(Document document) throws Exception {
        TableHeader tableHeader = constructHeader(document);
        return new Table(tableHeader, constructRows(tableHeader, document));
    }

    public Table constructTable(String path) throws Exception {
        Document document = generateDocument(path);
        return constructTable(document);
    }

}
