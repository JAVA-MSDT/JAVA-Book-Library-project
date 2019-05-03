package com.epam.library.util;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesExtractorTest {

    private final static String DB_NAME = "library";
    private final static String KEY = "db.name";
    private final static String PROPERTIES_FILE_NAME = "dataBase";

    @Test
    public void getValueFromPropertiesPass(){
        String db_name = PropertiesExtractor.getValueFromProperties(KEY, PROPERTIES_FILE_NAME);

        Assert.assertEquals(DB_NAME,db_name);
    }
}
