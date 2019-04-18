package com.epam.library.model.dao;

import com.epam.library.entity.Order;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.dao.query.OrderQuery;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    public OrderDao(Connection connection) {
        super(connection);
    }

    public OrderDao() {

    }

    @Override
    public Optional<Order> getById(long id) throws DaoException {
        return executeSingleResponseQuery(OrderQuery.SELECT_ORDER_BY_ID, new OrderBuilder(), String.valueOf(id));
    }

    @Override
    public List<Order> getAll() throws DaoException {
        return executeQuery(OrderQuery.SELECT_ALL_ORDERS, new OrderBuilder());
    }

    @Override
    public void save(Order item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at OrderDao class");

        String[] orderInfo = {String.valueOf(item.getBookId()), String.valueOf(item.getUserId()), String.valueOf(item.getOrderDate()),
                String.valueOf(item.getReturningDate()), item.getReadingPlace().name()};
        executeUpdate(OrderQuery.INSERT_ORDER, orderInfo);
    }

    @Override
    public void removeById(long id) throws DaoException {
        executeUpdate(OrderQuery.DELETE_ORDER, String.valueOf(id));
    }

    @Override
    public void update(Order item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in update at OrderDao class");

        String isReturned = item.isBookReturned() ? "1" : "0";
        String[] orderInfo = {String.valueOf(item.getBookId()), String.valueOf(item.getUserId()), String.valueOf(item.getOrderDate()),
                String.valueOf(item.getReturningDate()), item.getReadingPlace().name(), isReturned, String.valueOf(item.getOrderId())};

        executeUpdate(OrderQuery.UPDATE_ORDER_DATA, orderInfo);
    }
}
