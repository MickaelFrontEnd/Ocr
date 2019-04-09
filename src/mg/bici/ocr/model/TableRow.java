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
public class TableRow {
    private LocalisableWord designation;
    private LocalisableNumber quantity;
    private LocalisableNumber unitPrice;
    private LocalisableNumber totalPrice;
    private LocalisableNumber tva;
    private LocalisableNumber discount;

    public LocalisableWord getDesignation() {
        return designation;
    }

    public void setDesignation(LocalisableWord designation) {
        this.designation = designation;
    }

    public LocalisableNumber getQuantity() {
        return quantity;
    }

    public void setQuantity(LocalisableNumber quantity) {
        this.quantity = quantity;
    }

    public LocalisableNumber getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(LocalisableNumber unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalisableNumber getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(LocalisableNumber totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalisableNumber getTva() {
        return tva;
    }

    public void setTva(LocalisableNumber tva) {
        this.tva = tva;
    }

    public LocalisableNumber getDiscount() {
        return discount;
    }

    public void setDiscount(LocalisableNumber discount) {
        this.discount = discount;
    }

    public TableRow() {
    }

    public TableRow(LocalisableWord designation, LocalisableNumber quantity, LocalisableNumber unitPrice, LocalisableNumber totalPrice) {
        this.designation = designation;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Quantité:%.5f,\nDésignation:%s\nPrix unitaire:%.5f\nPrix total:%.5f",
                this.getQuantity().getNumber(),
                "",
                this.getUnitPrice().getNumber(),
                this.getTotalPrice().getNumber()
        );
    }

}
