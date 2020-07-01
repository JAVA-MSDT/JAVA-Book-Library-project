package com.javamsdt.library.model.service;

import java.util.List;
import java.util.Optional;

import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.dao.DaoException;
import com.javamsdt.library.model.dao.UserDao;
import com.javamsdt.library.util.validate.entityvalidate.UserValidator;

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
        try {
            userDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in removeById method in UserService class", e);
        }
    }

    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in update method in UserService class", e);
        }
    }


    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in findByLogin method in UserService class", e);
        }
    }

    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in findByEmail method in UserService class", e);
        }
    }


    // Librarian Query
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
    public List<User> sortUsersByName() throws ServiceException {
        try {
            return userDao.sortUsersByName();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in sortUsersByName method in UserService class", e);
        }
    }

    public List<User> sortUsersByEmail() throws ServiceException {
        try {
            return userDao.sortUsersByEmail();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in sortUsersByEmail method in UserService class", e);
        }
    }

    // Admin Query

    public void updateRole(Long id, String role) throws ServiceException {
        try {
            userDao.updateRole(id, role);
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in updateRole method in UserService class", e);
        }
    }

    public List<User> adminSortUsersByName() throws ServiceException {
        try {
            return userDao.adminSortUsersByName();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in adminSortUsersByName method in UserService class", e);
        }
    }

    public List<User> adminSortUsersByEmail() throws ServiceException {
        try {
            return userDao.adminSortUsersByEmail();
        } catch (DaoException e) {
            throw new ServiceException("Dao Exception in adminSortUsersByEmail method in UserService class", e);
        }
    }
}
