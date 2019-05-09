package com.epam.library.model.service;

import com.epam.library.model.db.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public final static boolean DISABLE_AUTO_COMMIT = false;
    private Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public TransactionManager() {
        connection = ConnectionPool.getInstance().getConnection();
    }

    public Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }


    /**
     * To set the autoCommit to be false before committing the change to the database
     * @throws SQLException if something wrong happens during the autoCommit setting
     */
    public void startTransaction() throws SQLException {
        connection.setAutoCommit(DISABLE_AUTO_COMMIT);
    }

    /**
     * To commit the change to the database
     * @throws SQLException if something wrong happens during the autoCommit setting
     */
    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    /**
     * will be triggered if something wrong happen with committing the change to the database
     */
    public void rollbackTransaction() {
        System.out.println("Before rollback try");
        try {
            System.out.println("Before rollback");
            connection.rollback();
            System.out.println("After rollback");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
