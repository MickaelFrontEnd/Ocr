/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import mg.bici.ocr.core.Dictionary;
import org.json.simple.JSONObject;

/**
 *
 * @author Mickael
 */
public class GeneralInformation {
    private String billNumber;
    private String issueDate;
    private String dueDate;

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public GeneralInformation(String billNumber, String issueDate, String dueDate) {
        this.billNumber = billNumber;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put(Dictionary.BILL_NUMBER_LABEL, getBillNumber());
        json.put(Dictionary.ISSUE_DATE_LABEL, getIssueDate());
        json.put(Dictionary.DUE_DATE_LABEL, getDueDate());
        return json;
    }
}
