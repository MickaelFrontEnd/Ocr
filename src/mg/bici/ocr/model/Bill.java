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
public class Bill {

    private GeneralInformation generalInformation;
    private Table table;

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Bill(GeneralInformation generalInformation, Table table) {
        this.generalInformation = generalInformation;
        this.table = table;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        if (getGeneralInformation() != null) {
            json.put(Dictionary.HEADER, getGeneralInformation().toJSON());
        }
        if (getTable() != null) {
            json.put(Dictionary.BODY, getTable().toJSON());
        }
        return json;
    }

    public String toJSON() {
        return toJSONObject().toJSONString();
    }
}
