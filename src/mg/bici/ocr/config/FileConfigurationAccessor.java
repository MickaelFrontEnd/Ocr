/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.bici.ocr.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author Mickael
 */
public class FileConfigurationAccessor implements ConfigurationAccessor {
    private Properties properties;
    private static final String PATH = "config.properties";

    public FileConfigurationAccessor() {
        this.init();
    }

    @Override
    public void init() {
        properties = new Properties();
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(PATH), "UTF8")) {
            properties.load(input);
        } catch (Exception ex) {

        }
    }

    @Override
    public String get(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String[] getDictionary(String key) {
        String dictionaries = get(key);
        return dictionaries.split(",", -1);
    }
}
