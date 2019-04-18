package com.epam.library.model.dao;

import com.epam.library.model.builder.UserBuilder;
import com.epam.library.entity.User;
import com.epam.library.model.dao.query.UserQuery;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    public UserDao(Connection connection){
        super(connection);
    }

    public UserDao(){

    }
    @Override

    public Optional<User> getById(long id) throws DaoException {

            return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_ID, new UserBuilder(),String.valueOf(id));
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(UserQuery.SELECT_ALL_USERS, new UserBuilder());
    }

    /**
     * @param item the user to be saved
     * @throws DaoException if something went wrong
     */
    @Override
    public void save(User item) throws DaoException {
        ArgumentValidator.checkForNull(item, "User item in save method at UserDao class can not be null");

        String[] userInfo = {item.getName(), item.getLastName(), item.getEmail(), item.getLogin(), item.getPassword()};
        executeUpdate(UserQuery.INSERT_USER, userInfo);
    }

    @Override
    public void removeById(long id) throws DaoException {
        executeUpdate(UserQuery.DELETE_USER, String.valueOf(id));
    }

    /**
     *
     * @param item user to be update by the Librarian.
     * @throws DaoException
     */
    @Override
    public void update(User item) throws DaoException{
        ArgumentValidator.checkForNull(item, "User item in update method at UserDao class can not be null");

        String blockStatues = item.isBlocked() ? "1" : "0";
        String[] userUpdate = {item.getName(), item.getLastName(), item.getEmail(), item.getLogin(),
                item.getPassword(), blockStatues, String.valueOf(item.getId())};

        executeUpdate(UserQuery.LIBRARIAN_UPDATE_USER_DATA, userUpdate);

    }

    /**
     *
     * @param user to be updated by the Admin, just to set the role.
     * @throws DaoException
     */
    public void userUpdateByAdmin(User user) throws DaoException {

        String blockStatues = user.isBlocked() ? "1" : "0";
        String[] userUpdate = {user.getName(), user.getLastName(), user.getEmail(), user.getLogin(),
                user.getPassword(), user.getRole().name(), blockStatues, String.valueOf(user.getId())};

        executeUpdate(UserQuery.ADMIN_UPDATE_USER_DATA, userUpdate);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) throws DaoException {
        ArgumentValidator.checkForNullOrEmptyString(login,"Not allow for a null or empty login in findByLoginAndPassword at UserDao class");
        ArgumentValidator.checkForNullOrEmptyString(login,"Not allow for a null or empty password in findByLoginAndPassword at UserDao class");

        return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_LOGIN_PASSWORD, new UserBuilder(), login, password);
    }
}
