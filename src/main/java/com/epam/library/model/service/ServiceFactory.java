package com.epam.library.model.service;

import com.epam.library.model.dao.DaoFactory;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private UserService userService = new UserService(daoFactory.getUserDao());

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return new UserService(daoFactory.getUserDao());
    }

    public BookService getBookService(){
        return new BookService(daoFactory.getBookDao());
    }
}
