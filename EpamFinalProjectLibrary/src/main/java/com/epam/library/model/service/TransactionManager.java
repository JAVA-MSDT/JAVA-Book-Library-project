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

    public void setAutoCommit() throws ServiceException {
        try {
            connection.setAutoCommit(DISABLE_AUTO_COMMIT);
        } catch (SQLException e) {
            throw new ServiceException("Unable To setAutoCommit transaction", e);
        }
    }

    public void startTransaction() throws ServiceException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException("Unable To Start transaction", e);
        }
    }

    public void commitTransaction() throws ServiceException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException("Unable To Commit transaction", e);
        }
    }

    public void rollbackTransaction() throws ServiceException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new ServiceException("Unable To Rollback transaction", e);
        }
    }
}
