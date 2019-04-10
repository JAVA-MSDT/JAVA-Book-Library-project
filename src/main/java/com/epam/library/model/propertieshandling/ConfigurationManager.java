package com.epam.library.model.propertieshandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static String configLocation = "src/main/resources/config.properties";
    static FileInputStream fileInputStream;

    {
        try {
            fileInputStream = new FileInputStream("config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("/resources/config.properties", Locale.getDefault());

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
