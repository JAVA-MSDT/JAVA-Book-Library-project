package com.javamsdt.library.model.service;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public final static boolean DISABLE_AUTO_COMMIT = false;
    private Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
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
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
