package com.javamsdt.library.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javamsdt.library.entity.Order;
import com.javamsdt.library.model.builder.AdministrationOrderBuilder;
import com.javamsdt.library.model.builder.OrderBuilder;
import com.javamsdt.library.model.builder.UserOrderBuilder;
import com.javamsdt.library.model.dto.orderservice.AdministrationOrderDisplay;
import com.javamsdt.library.model.dto.orderservice.UserOrderDisplay;
import com.javamsdt.library.util.constant.query.OrderQuery;
import com.javamsdt.library.util.validate.ArgumentValidator;

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

    public Optional<Order> findOrderByBookId(Long bookId) throws DaoException {
        return executeSingleResponseQuery(OrderQuery.SELECT_ORDER_BY_BOOK_ID, new OrderBuilder(), String.valueOf(bookId));
    }


    /**
     *
     * @return list of a special object to use it later to display the detailed order
     * information for the administration and admin
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
            throw new DaoException(e);
        }
        return orderDisplayList;
    }

    /**
     * @return list of a special object to use it later to display the detailed order
     * information for the User
     * @throws DaoException is something wrong during the process
     */
    public List<UserOrderDisplay> userOrders() throws DaoException {
        List<UserOrderDisplay> orderDisplayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(OrderQuery.SELECT_ORDER_FOR_USER);

            prepareStatement(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserOrderBuilder builder = new UserOrderBuilder();
                UserOrderDisplay orderDisplay = builder.build(resultSet);
                orderDisplayList.add(orderDisplay);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderDisplayList;
    }
}
