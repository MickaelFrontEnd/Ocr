/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mg.bici.ocr.core.Dictionnary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mickael
 */
public class Table {
    private TableHeader tableHeader;
    private List<TableRow> tableRow;

    public TableHeader getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(TableHeader tableHeader) {
        this.tableHeader = tableHeader;
    }

    public List<TableRow> getTableRow() {
        return tableRow;
    }

    public void setTableRow(List<TableRow> tableRow) {
        this.tableRow = tableRow;
    }

    public Table(TableHeader tableHeader, List<TableRow> tableRow) {
        this.tableHeader = tableHeader;
        this.tableRow = tableRow;
    }

    public List<Map<String, Object>> toKeyValue() {
        List<Map<String, Object>> result = new ArrayList();
        Map<String, Object> keyValue = null;
        for (TableRow row : getTableRow()) {
            keyValue = new HashMap();
            keyValue.put(Dictionnary.QUANTITY_LABEL, row.getQuantity().getNumber());
            keyValue.put(Dictionnary.DESIGNATION_LABEL, row.getDesignation().getWord());
            keyValue.put(Dictionnary.UNIT_PRICE_LABEL, row.getUnitPrice().getNumber());
            keyValue.put(Dictionnary.TOTAL_PRICE_LABEL, row.getTotalPrice().getNumber());
            result.add(keyValue);
        }
        return result;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonRow = null;
        List<TableRow> rows = getTableRow();
        for (TableRow row : rows) {
            jsonRow = new JSONObject();
            jsonRow.put(Dictionnary.TOTAL_PRICE_LABEL, row.getTotalPrice().getNumber());
            jsonRow.put(Dictionnary.QUANTITY_LABEL, row.getQuantity().getNumber());
            jsonRow.put(Dictionnary.DESIGNATION_LABEL, "");
            jsonRow.put(Dictionnary.UNIT_PRICE_LABEL, row.getUnitPrice().getNumber());
            jsonArray.add(jsonRow);
        }
        json.put("facture", jsonArray);
        return json.toJSONString();
    }
}
