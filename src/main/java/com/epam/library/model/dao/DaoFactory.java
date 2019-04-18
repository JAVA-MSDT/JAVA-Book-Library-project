package com.epam.library.model.dao;

import com.epam.library.model.db.ConnectionPool;

import java.sql.Connection;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();
    private final Connection connection = ConnectionPool.getInstance().getConnection();
    private  UserDao userDao = new UserDao(connection);
    private  BookDao bookDao = new BookDao(connection);
    private  OrderDao orderDao = new OrderDao(connection);

    private DaoFactory(){

    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
