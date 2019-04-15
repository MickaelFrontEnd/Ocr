/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public JSONObject getJSONObject(File file) throws Exception {
        try {
            return getCore().constructBill(file).toJSONObject();
        } catch (Exception ex) {
            throw new Exception("Impossible de lire la facture");
        }
    }

    public JSONObject getJSONObject(String path) throws Exception {
        try {
            return getCore().constructBill(path).toJSONObject();
        } catch (Exception ex) {
            throw new Exception("Impossible de lire la facture");
        }
    }

    public String getJSON(String path) throws Exception {
        try {
            return getCore().constructBill(path).toJSON();
        } catch (Exception ex) {
            throw new Exception("Impossible de lire la facture");
        }
    }

    public String getPlainText(String path) throws Exception {
        return getCore().generatePlainText(path);
    }

    // Information générale
    public Object getHeader(JSONObject json, Class classe, Map<String, String> mapping) throws Exception {
        return Converter.convert((JSONObject) json.get(Dictionary.HEADER), classe, mapping);
    }

    // Lignes factures
    public List<Object> getBody(JSONObject json, Class classe, Map<String, String> mapping) throws Exception {
        List<Object> result = new ArrayList();
        JSONArray rows = (JSONArray) json.get(Dictionary.BODY);
        JSONObject row;
        for (int i = 0; i < rows.size(); i++) {
            row = (JSONObject) rows.get(i);
            result.add(Converter.convert(row, classe, mapping));
        }
        return result;
    }
}
