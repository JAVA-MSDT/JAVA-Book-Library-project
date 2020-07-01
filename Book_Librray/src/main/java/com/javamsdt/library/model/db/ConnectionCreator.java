package com.javamsdt.library.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.javamsdt.library.util.PropertiesExtractor;

public class ConnectionCreator {
    private final static String DB_PROPERTIES_URI = "dataBase";
    private final static String DRIVER = "db.driver";
    private final static String NAME = "db.name";
    private final static String URI = "db.uri";
    private final static String LOGIN = "db.login";
    private final static String PASSWORD = "db.password";

    private String dbName ;
    private String dbURI ;
    private String dbLogin;
    private String dbPassword ;

    // package-private
    ConnectionCreator() {
        dbName = PropertiesExtractor.getValueFromProperties(NAME, DB_PROPERTIES_URI);
        dbURI = PropertiesExtractor.getValueFromProperties(URI, DB_PROPERTIES_URI);
        dbLogin = PropertiesExtractor.getValueFromProperties(LOGIN, DB_PROPERTIES_URI);
        dbPassword = PropertiesExtractor.getValueFromProperties(PASSWORD, DB_PROPERTIES_URI);
    }

    public Connection create() throws SQLException {
        String driver = PropertiesExtractor.getValueFromProperties(DRIVER, DB_PROPERTIES_URI);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return DriverManager.getConnection(dbURI + dbName, dbLogin, dbPassword);

    }
}
