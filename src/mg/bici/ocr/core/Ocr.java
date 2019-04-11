/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.util.List;
import java.util.Map;
import mg.bici.ocr.model.Table;

/**
 *
 * @author Mickael
 */
public class Ocr {
    private Core core;

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Ocr() {
        core = new Core();
    }

    public String getJSON(String path) throws Exception {
        Table table = getCore().constructTable(path);
        return table.toJson();
    }

    // Information générale
    public Object getHeader(Class classe, Map<String, String> mapping) {
        return null;
    }

    // Lignes factures
    public List<Object> getRows(Class classe, Map<String, String> mapping) {
        return null;
    }
}
