/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.model;

import java.util.List;

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

}
