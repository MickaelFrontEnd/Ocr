/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.config;

/**
 *
 * @author Mickael
 */
public interface ConfigurationAccessor {
    public void init();

    public String get(String key);

    public String[] getDictionary(String key);
}
