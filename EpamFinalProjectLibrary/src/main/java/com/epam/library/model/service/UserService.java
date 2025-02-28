package com.epam.library.model.service;

import com.epam.library.entity.User;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.UserDao;
import com.epam.library.util.validate.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param id of the user in the database
     * @return user if it is exist or empty optional
     * @throws ServiceException
     */
    public Optional<User> getById(long id) throws ServiceException {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in getById method in UserService class", e);
        }
    }

    /**
     * @param login    of the user
     * @param password of the user
     * @return user if it is exist or empty optional
     * @throws ServiceException
     */
    public Optional<User> findByLoginPassword(String login, String password) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        if (UserValidator.isValidLogin(login) && UserValidator.isValidPassword(password)) {
            try {
                optionalUser = userDao.findByLoginAndPassword(login, password);
            } catch (DaoException e) {
                throw new ServiceException("Dao Exception in findByLoginPassword method in UserService class", e);
            }
        }
        return optionalUser;
    }

    /**
     * @return List of the users from the Dao
     * @throws ServiceException
     */
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in getAll method in UserService class", e);
        }
    }

    public void save(User user) throws ServiceException {
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in save method in UserService class", e);
        }
    }

    @Override
    public void removeById(long id) throws ServiceException {

    }

    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in update method in UserService class", e);
        }
    }

    /**
     * @return list of user by the role Reader
     * @throws ServiceException if something wrong happens while executing the query
     */
    public List<User> findAllWhereRoleReader() throws ServiceException {
        try {
            return userDao.findAllWhereRoleReader();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in findAllWhereRoleReader method in UserService class", e);
        }
    }

}
