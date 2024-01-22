package com.javamsdt.library.model.db;

import com.javamsdt.library.util.PropertiesExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final Logger LOGGER = LogManager.getLogger();
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
        // return getDataSource().getConnection();
    }

//    private DataSource getDataSource() {
//
//        DataSource dataSource = null;
//        EmbeddedPostgres postgres;
//
//        try {
//            postgres = EmbeddedPostgres.builder().start();
//            dataSource = postgres.getPostgresDatabase();
//        } catch (IOException e) {
//            LOGGER.error("Unabling to Start EmbededPostgress: " + e);
//        }
//        return dataSource;
//    }
    public Flyway flyway() {
        // FluentConfiguration configuration = Flyway.configure().dataSource(getDataSource());
        FluentConfiguration configuration = Flyway.configure().dataSource(dbURI, dbLogin, dbPassword);
        return new Flyway(configuration);
    }
}
