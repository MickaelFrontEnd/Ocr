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
public class TableHeader {
    private LocalisableWord quantity; 
    private LocalisableWord designation;
    private LocalisableWord unitPrice;
    private LocalisableWord totalPrice;
    private LocalisableWord tva;
    private LocalisableWord discount;

    public LocalisableWord getQuantity() {
        return quantity;
    }

    public void setQuantity(LocalisableWord quantity) {
        this.quantity = quantity;
    }

    public LocalisableWord getDesignation() {
        return designation;
    }

    public void setDesignation(LocalisableWord designation) {
        this.designation = designation;
    }

    public LocalisableWord getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(LocalisableWord unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalisableWord getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(LocalisableWord totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalisableWord getTva() {
        return tva;
    }

    public void setTva(LocalisableWord tva) {
        this.tva = tva;
    }

    public LocalisableWord getDiscount() {
        return discount;
    }

    public void setDiscount(LocalisableWord discount) {
        this.discount = discount;
    }   

    public TableHeader(LocalisableWord quantity, LocalisableWord designation, LocalisableWord unitPrice, LocalisableWord totalPrice, LocalisableWord tva) {
        this.quantity = quantity;
        this.designation = designation;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.tva = tva;
    }
    
    
}
