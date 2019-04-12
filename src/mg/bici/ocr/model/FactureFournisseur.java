/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import java.sql.Date;

/**
 *
 * @author Mickael
 */
public class FactureFournisseur {
    public String numFact;
    public String idFactureFournisseur;
    public java.sql.Date daty;
    public String idFournisseur;
    public String idDevise;
    public double montantTTC;
    public String remarque;
    public double idTVA;
    public String designation;
    public java.sql.Date dateEmission;
    private String resp;
    private java.sql.Date datyecheance;

    public String getNumFact() {
        return numFact;
    }

    public void setNumFact(String numFact) {
        this.numFact = numFact;
    }

    public String getIdFactureFournisseur() {
        return idFactureFournisseur;
    }

    public void setIdFactureFournisseur(String idFactureFournisseur) {
        this.idFactureFournisseur = idFactureFournisseur;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public double getIdTVA() {
        return idTVA;
    }

    public void setIdTVA(double idTVA) {
        this.idTVA = idTVA;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Date getDatyecheance() {
        return datyecheance;
    }

    public void setDatyecheance(Date datyecheance) {
        this.datyecheance = datyecheance;
    }

    public FactureFournisseur() {
    }
}
