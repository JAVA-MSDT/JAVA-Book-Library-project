package com.epam.library.model.db;

import com.epam.library.util.PropertiesExtractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private final static String DB_PROPERTIES_URI = "dataBase";
    private final static String NAME = "db.name";
    private final static String URI = "db.uri";
    private final static String LOGIN = "db.login";
    private final static String PASSWORD = "db.password";


    public static Connection getConnection() throws SQLException {
        String dbName = PropertiesExtractor.getValueFromProperties(NAME, DB_PROPERTIES_URI);
        String dbURI = PropertiesExtractor.getValueFromProperties(URI, DB_PROPERTIES_URI);
        String dbLogin = PropertiesExtractor.getValueFromProperties(LOGIN, DB_PROPERTIES_URI);
        String dbPassword = PropertiesExtractor.getValueFromProperties(PASSWORD, DB_PROPERTIES_URI);
       return DriverManager.getConnection(dbURI + dbName, dbLogin, dbPassword);

    }
}
