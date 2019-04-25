package com.epam.library.model.service;

import com.epam.library.entity.User;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.DaoFactory;
import com.epam.library.model.dao.UserDao;
import com.epam.library.util.validate.DataMatcher;
import com.epam.library.util.validate.DataRegex;
import com.epam.library.util.validate.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User>{

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public UserService(){

    }

    /**
     *
     * @param id of the user in the database
     * @return user if it is exist or empty optional
     * @throws ServiceException
     */
    public Optional<User> findById(long id) throws ServiceException {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in findById method in UserService class",e);
        }
    }

    /**
     *
     * @param login of the user
     * @param password of the user
     * @return user if it is exist or empty optional
     * @throws ServiceException
     */
    public Optional<User> findByLoginPassword(String login, String password) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        if(UserValidator.isValidLogin(login) && UserValidator.isValidPassword(password)) {
            try {
                optionalUser = userDao.findByLoginAndPassword(login, password);
            } catch (DaoException e) {
                throw new ServiceException("Dao Exception in findByLoginPassword method in UserService class", e);
            }
        }
        return optionalUser;
    }

    /**
     *
     * @return List of the users from the Dao
     * @throws ServiceException
     */
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in getAllUsers method in UserService class", e);
        }
    }

    public void saveUser(User user) throws ServiceException {
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in save method in UserService class", e);
        }
    }

    public void librarianUpdateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in librarianUpdateUser method in UserService class", e);
        }
    }

}
