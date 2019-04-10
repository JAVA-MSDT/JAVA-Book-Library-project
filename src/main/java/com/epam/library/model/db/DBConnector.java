package com.epam.library.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws SQLException {

        Connection connection = null;
        try {
            Class.forName(DBInfo.DB_DRIVER);
            connection = DriverManager.getConnection(DBInfo.DB_URI + DBInfo.DB_NAME, DBInfo.LOGIN, DBInfo.PASSWORD);;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
