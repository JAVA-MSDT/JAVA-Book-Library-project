package com.javamsdt.library.model.service;

import java.sql.Connection;

import com.javamsdt.library.model.dao.DaoFactory;

public class ServiceFactory {

    private DaoFactory daoFactory;

    public ServiceFactory(Connection connection) {
        daoFactory = new DaoFactory(connection);
    }

    public UserService getUserService() {
        return new UserService(daoFactory.getUserDao());
    }

    public BookService getBookService() {
        return new BookService(daoFactory.getBookDao());
    }

    public OrderService getOrderService() {
        return new OrderService(daoFactory.getOrderDao());
    }

}
