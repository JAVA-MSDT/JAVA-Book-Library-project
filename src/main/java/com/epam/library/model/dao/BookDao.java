package com.epam.library.model.dao;

import com.epam.library.entity.Book;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.dao.query.BookQuery;
import com.epam.library.util.validate.ArgumentValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao extends AbstractDao<Book> {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Book> getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(BookQuery.SELECT_BOOK_BY_ID);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                BookBuilder bookBuilder = new BookBuilder();
                Book book = bookBuilder.build(resultSet);
                return Optional.of(book);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "SqlException in getById method at BookDao class", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(ps);
            closeConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Book> books = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(BookQuery.SELECT_ALL_BOOKS);

            while (resultSet.next()) {
                BookBuilder bookBuilder = new BookBuilder();
                Book book = bookBuilder.build(resultSet);
                books.add(book);
            }

        } catch (SQLException e) {
            throw new DaoException("SqlException in getAll method at BookDao class", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return books;
    }

    @Override
    public void save(Book item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at BookDao class");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(BookQuery.INSERT_BOOK);
            ps.setString(1, item.getName());
            ps.setString(2, String.valueOf(item.getQuantity()));
            ps.execute();
        } catch (SQLException e) {
            throw new DaoException("SqlException in save method at BookDao class", e);
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
            ps = connection.prepareStatement(BookQuery.DELETE_BOOK);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SqlException in removeById method at BookDao class", e);
        } finally {
            closeStatement(ps);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Book item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in update at UserDao class");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(BookQuery.UPDATE_BOOK);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getQuantity());
            ps.setLong(3, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SqlException in update method at BookDao class", e);
        } finally {
            closeStatement(ps);
            closeConnection(connection);
        }
    }
}
