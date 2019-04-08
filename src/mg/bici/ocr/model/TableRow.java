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
    private String designation;
    private double unit;
    private double unitPrice;
    private double totalPrice;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }   
}
