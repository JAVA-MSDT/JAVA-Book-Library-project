package com.epam.library.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws SQLException {

        return  DriverManager.getConnection(DBInfo.DB_URI + DBInfo.DB_NAME, DBInfo.LOGIN, DBInfo.PASSWORD);

    }
}
