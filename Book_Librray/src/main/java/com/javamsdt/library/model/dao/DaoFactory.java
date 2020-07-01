package com.javamsdt.library.model.dao;

import java.sql.Connection;

public class DaoFactory {

    private final Connection connection;

    public DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public UserDao getUserDao() {
        return new UserDao(connection);
    }

    public BookDao getBookDao() {
        return new BookDao(connection);
    }

    public OrderDao getOrderDao() {
        return new OrderDao(connection);
    }

}
