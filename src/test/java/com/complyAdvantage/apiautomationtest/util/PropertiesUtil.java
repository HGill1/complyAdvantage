package com.complyAdvantage.apiautomationtest.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtil {
    private static PropertiesUtil instance;
    private final Properties properties = new Properties();

    private PropertiesUtil() {
        loadProperties();
    }

    public static synchronized PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public String getApiURI(String URI) {
        return properties.getProperty("baseUrl").concat(properties.getProperty(URI));
    }

    private void loadProperties() {
        try {
            InputStream iStream = new FileInputStream("src/test/resources/properties/config.properties");
            properties.load(iStream);
        } catch (Exception exception) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
