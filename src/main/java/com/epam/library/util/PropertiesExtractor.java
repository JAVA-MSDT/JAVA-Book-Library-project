package com.epam.library.util;

import java.util.ResourceBundle;

public class PropertiesExtractor {

    public static String getValueFromProperties(String key, String propertiesURI){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesURI);
        return resourceBundle.getString(key);
    }
}
