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
import mg.bici.ocr.core.Dictionary;
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
        Map<String, Object> keyValue;
        for (TableRow row : getTableRow()) {
            keyValue = new HashMap();
            keyValue.put(Dictionary.QUANTITY_LABEL, row.getQuantity().getNumber());
            keyValue.put(Dictionary.DESIGNATION_LABEL, row.getDesignation().getWord());
            keyValue.put(Dictionary.UNIT_PRICE_LABEL, row.getUnitPrice().getNumber());
            keyValue.put(Dictionary.TOTAL_PRICE_LABEL, row.getTotalPrice().getNumber());
            if (row.getTva() != null) {
                keyValue.put(Dictionary.TVA_LABEL, row.getTva().getNumber());
            }
            result.add(keyValue);
        }
        return result;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonRow;
        List<TableRow> rows = getTableRow();
        for (TableRow row : rows) {
            jsonRow = new JSONObject();
            jsonRow.put(Dictionary.TOTAL_PRICE_LABEL, row.getTotalPrice().getNumber());
            jsonRow.put(Dictionary.QUANTITY_LABEL, row.getQuantity().getNumber());
            jsonRow.put(Dictionary.DESIGNATION_LABEL, row.getDesignation().getWord());
            jsonRow.put(Dictionary.UNIT_PRICE_LABEL, row.getUnitPrice().getNumber());
            if (row.getTva() != null) {
                jsonRow.put(Dictionary.TVA_LABEL, row.getTva().getNumber());
            }
            jsonArray.add(jsonRow);
        }
        json.put("facture", jsonArray);
        return json.toJSONString();
    }
}
