package com.epam.library.model.service;

import com.epam.library.model.dao.DaoFactory;

import java.sql.Connection;

public class ServiceFactory {

    private DaoFactory daoFactory;

    public ServiceFactory(Connection connection){
        daoFactory = new DaoFactory(connection);
    }

    public UserService getUserService() {
        return new UserService(daoFactory.getUserDao());
    }

    public BookService getBookService(){
        return new BookService(daoFactory.getBookDao());
    }

    public OrderService getOrderService(){
        return new OrderService(daoFactory.getOrderDao());
    }

}
