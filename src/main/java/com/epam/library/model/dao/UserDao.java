package com.epam.library.model.dao;

import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.entity.User;
import com.epam.library.model.dao.query.UserQuery;
import com.epam.library.util.validate.ArgumentValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private final static Logger logger = LogManager.getLogger();
    @Override
    public Optional<User> getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user;
        UserBuilder userBuilder = new UserBuilder();
        Optional<User> userOptional;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(UserQuery.SELECT_USER_BY_ID);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if(resultSet.next()) {
                user = userBuilder.build(resultSet);
                userOptional = Optional.of(user);
                return userOptional;
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR,"SqlException in getById method at UserDao class", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(ps);
            closeConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        UserBuilder userBuilder = new UserBuilder();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(UserQuery.SELECT_ALL_USERS);
            while (resultSet.next()) {

                User user = userBuilder.build(resultSet);
                users.add(user);
            }
        }  catch (SQLException e) {
            throw new DaoException("SqlException in getAll method at UserDao class",e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public void save(User item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at UserDao class");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UserQuery.INSERT_USER);
            ps.setString(1, item.getName());
            ps.setString(2, item.getLastName());
            ps.setString(3, item.getEmail());
            ps.setString(4, item.getLogin());
            ps.setString(5, item.getPassword());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.log(Level.ERROR, "Cannot rollback the transaction in save method at UserDao class");
            }
            throw new DaoException("SqlException for save method at UserDao class",e);
        }finally {
            closeStatement(ps);
            closeConnection(connection);
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UserQuery.DELETE_USER);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException("SqlException in removeById at UserDao class",e);
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(User item) throws DaoException{
        ArgumentValidator.checkForNull(item, "Not allow for a null item in update at UserDao class");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            System.out.println("Starting update");
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UserQuery.UPDATE_USER_DATA);

            ps.setString(1, item.getName());
            ps.setString(2, item.getLastName());
            ps.setString(3, item.getEmail());
            ps.setString(4, item.getLogin());
            ps.setString(5, item.getPassword());
            ps.setString(6, item.getRole().name());
            ps.setBoolean(7, item.isBlocked());
            ps.setLong(8, item.getId());

            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                logger.log(Level.ERROR, "Connection rollback the transaction in update method at UserDao class");
                connection.rollback();
            } catch (SQLException e1) {
                logger.log(Level.ERROR, "Cannot rollback the transaction in update method at UserDao class");
            }
            throw new DaoException("SqlException in update at UserDao class",e);
        }
    }

    public User findByLoginAndPassword(String login, String password) throws DaoException {
        ArgumentValidator.checkForNullOrEmptyString(login,"Not allow for a null or empty login in findByLoginAndPassword at UserDao class");
        ArgumentValidator.checkForNullOrEmptyString(login,"Not allow for a null or empty password in findByLoginAndPassword at UserDao class");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        UserBuilder userBuilder = new UserBuilder();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UserQuery.SELECT_USER_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = userBuilder.build(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("SqlException in findByLoginAndPassword at UserDao class",e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return user;
    }
}
