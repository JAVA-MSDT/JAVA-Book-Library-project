package com.epam.library.model.dao;

import com.epam.library.entity.Order;
import com.epam.library.model.builder.AdministrationOrderBuilder;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.dao.query.OrderQuery;
import com.epam.library.model.service.orderservice.AdministrationOrderDisplay;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Order> findOrderByUserId(long userId) throws DaoException {
        return executeQuery(OrderQuery.SELECT_ORDER_BY_USER_ID, new OrderBuilder(), String.valueOf(userId));
    }

    /**
     *
     * @return list of a special class to use it later to display the detailed order
     * information for the librarian and admin
     * @throws DaoException is something wrong during the process
     */
    public List<AdministrationOrderDisplay> administrationAllOrder() throws DaoException {
        List<AdministrationOrderDisplay> orderDisplayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(OrderQuery.SELECT_ORDER_FOR_REVIEW);

            prepareStatement(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AdministrationOrderBuilder builder = new AdministrationOrderBuilder();
                AdministrationOrderDisplay orderDisplay = builder.build(resultSet);
                orderDisplayList.add(orderDisplay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDisplayList;
    }

}
