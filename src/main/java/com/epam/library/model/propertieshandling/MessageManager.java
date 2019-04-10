package com.epam.library.model.propertieshandling;

import java.util.ResourceBundle;

public class MessageManager {
    private final static String messageLocation = "src/main/resources/config.properties";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(messageLocation);

    private MessageManager(){

    }
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
