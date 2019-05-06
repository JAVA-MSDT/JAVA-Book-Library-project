package com.epam.library.util;

import java.util.ResourceBundle;

public class PropertiesExtractor {

    /**
     * @param key                of the value that we need to read
     * @param propertiesFileName name of the properties file name
     * @return value of the key which located in the specified properties file
     */
    public static String getValueFromProperties(String key, String propertiesFileName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesFileName);
        return resourceBundle.getString(key);
    }
}
