package com.epam.library.model.dao;

import com.epam.library.entity.Order;
import com.epam.library.model.builder.OrderBuilder;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.dao.query.OrderQuery;
import com.epam.library.util.validate.ArgumentValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Order> getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(OrderQuery.SELECT_ORDER_BY_ID);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if(resultSet.next()) {
                OrderBuilder orderBuilder = new OrderBuilder();
                Order order = orderBuilder.build(resultSet);

                return Optional.of(order);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "SqlException in getById method at OrderDao class", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(ps);
            closeConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Order> orders = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(OrderQuery.SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                OrderBuilder orderBuilder = new OrderBuilder();
                Order order = orderBuilder.build(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new DaoException("SqlException in getAll method at OrderDao class", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return orders;
    }

    @Override
    public void save(Order item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at OrderDao class");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(OrderQuery.INSERT_ORDER);
            ps.setLong(1, item.getBookId());
            ps.setLong(2, item.getUserId());
            ps.setDate(3, item.getOrderDate());
            ps.setDate(4, item.getReturningDate());
            ps.setString(5, item.getReadingPlace().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SqlException in save method at OrderDao class", e);
        } finally {
            closeStatement(ps);
            closeConnection(connection);
        }

    }

    @Override
    public void removeById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(OrderQuery.DELETE_ORDER);
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("SqlException in removeById method at OrderDao class", e);
        } finally {
            closeStatement(ps);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Order item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in update at OrderDao class");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(OrderQuery.UPDATE_ORDER_DATA);
            ps.setLong(1, item.getBookId());
            ps.setLong(2, item.getUserId());
            ps.setDate(3, item.getOrderDate());
            ps.setDate(4, item.getReturningDate());
            ps.setString(5, item.getReadingPlace().name());
            ps.setBoolean(6, item.isBookReturned());
            ps.setLong(7, item.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SqlException in update method at OrderDao class", e);
        } finally {
            closeStatement(ps);
            closeConnection(connection);
        }
    }
}
