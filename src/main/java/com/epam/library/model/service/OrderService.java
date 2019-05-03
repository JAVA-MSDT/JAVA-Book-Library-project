package com.epam.library.model.service;

import com.epam.library.entity.Order;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.OrderDao;
import com.epam.library.model.dao.query.OrderQuery;
import com.epam.library.util.validate.ArgumentValidator;

import java.util.List;
import java.util.Optional;

public class OrderService implements Service<Order> {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    public OrderService() {
    }

    public Optional<Order> getById(long id) throws ServiceException {
        try {
            return orderDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in getById at OrderService class",e);
        }
    }

    public List<Order> getAll() throws ServiceException {
        try {
            return orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("exception in getAll at OrderService class",e);
        }
    }

    public void save(Order order) throws ServiceException {
        ArgumentValidator.checkForNull(order, "Not allow for a null order in save at OrderService class");

        try {
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException("exception in save at OrderService class",e);
        }
    }

    public void removeById(long id) throws ServiceException {
        try {
            orderDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in removeById at OrderService class",e);
        }
    }

    public void update(Order order) throws ServiceException {
        ArgumentValidator.checkForNull(order, "Not allow for a null order in update at OrderService class");

        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("exception in update at OrderService class",e);
        }
    }

    public List<Order> findOrderByUserId(long userId) throws ServiceException {
        try {
            return orderDao.findOrderByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("exception in findOrderByUserId at OrderService class",e);
        }
    }
}
