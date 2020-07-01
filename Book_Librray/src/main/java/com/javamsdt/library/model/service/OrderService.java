package com.javamsdt.library.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.javamsdt.library.entity.Book;
import com.javamsdt.library.entity.Order;
import com.javamsdt.library.model.dao.DaoException;
import com.javamsdt.library.model.dao.OrderDao;
import com.javamsdt.library.model.dto.orderservice.AdministrationOrderDisplay;
import com.javamsdt.library.model.dto.orderservice.UserOrderDisplay;
import com.javamsdt.library.util.validate.ArgumentValidator;

public class OrderService implements Service<Order> {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<Order> getById(long id) throws ServiceException {
        try {
            return orderDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in getById at OrderService class", e);
        }
    }

    public List<Order> getAll() throws ServiceException {
        try {
            return orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("exception in getAll at OrderService class", e);
        }
    }

    public void save(Order order) throws ServiceException {
        ArgumentValidator.checkForNull(order, "Not allow for a null order in save at OrderService class");

        try {
            orderDao.save(order);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("exception in save at OrderService class", e);
        }
    }

    public void removeById(long id) throws ServiceException {
        try {
            orderDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in removeById at OrderService class", e);
        }
    }

    public void update(Order order) throws ServiceException {
        ArgumentValidator.checkForNull(order, "Not allow for a null order in update at OrderService class");

        try {
            orderDao.update(order);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("exception in update at OrderService class", e);
        }
    }

    public List<Order> findOrderByUserId(long userId) throws ServiceException {
        try {
            return orderDao.findOrderByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("exception in findOrderByUserId at OrderService class", e);
        }
    }

    public Optional<Order> findOrderByBookId(Long bookId) throws ServiceException {
        try {
            return orderDao.findOrderByBookId(bookId);
        } catch (DaoException e) {
            throw new ServiceException("exception in findOrderByBookId at OrderService class", e);
        }
    }


    /**
     *
     * @return list of a special object to use it later to display the detailed order
     * information for the administration and admin
     * @throws ServiceException if something wrong during the process
     */
    public List<AdministrationOrderDisplay> administrationAllOrder() throws ServiceException {
        try {
            return orderDao.administrationAllOrder();
        } catch (DaoException e) {
            throw new ServiceException("exception in administrationAllOrder at OrderService class", e);
        }
    }

    /**
     *
     * @param orderID to get the specified order to be removed
     * @param bookId to get the returning book and updated it by 1 after removing the order
     * @param bookService to pass it to the increasing book quantity
     * @param transactionManager to make sure that the operation done successfully
     * @throws ServiceException in case of something wrong happens during the process
     */
    public void administrationOrderRemoval(String orderID, String bookId, BookService bookService, TransactionManager transactionManager) throws ServiceException {
        try {
            transactionManager.startTransaction();

            increaseBookQuantity(bookId, bookService);
            removeById(Long.valueOf(orderID));
            transactionManager.commitTransaction();
        } catch (SQLException e) {
            transactionManager.rollbackTransaction();
        }


    }

    /**
     *
     * @param order to be confirmed and to added to the user order list
     * @param book to get the desired book then decrees it by 1 after the user order it
     * @param bookService to pass it to the decreasing book quantity
     * @param transactionManager to make sure that the operation done successfully
     * @throws ServiceException in case of something wrong happens during the process
     */
    public void confirmUserOrder(Order order, Book book, BookService bookService, TransactionManager transactionManager) throws ServiceException {
        try {
            transactionManager.startTransaction();

            save(order);
            decreaseBookQuantity(book, bookService);

            transactionManager.commitTransaction();
        } catch (SQLException e) {
            transactionManager.rollbackTransaction();
        }
    }

    /**
     * @return list of a special class to use it later to display the detailed order
     * information for the User
     * @throws ServiceException if something wrong during the process
     */
    public List<UserOrderDisplay> userOrders() throws ServiceException {
        try {
            return orderDao.userOrders();
        } catch (DaoException e) {
            throw new ServiceException("exception in userOrders at OrderService class", e);
        }
    }

    /**
     * To update the book quantity in the data base
     *
     * @param book that the user want to order it
     * @throws ServiceException if something wrong with updating method
     */
    private void decreaseBookQuantity(Book book, BookService bookService) throws ServiceException {
        Long id = book.getId();
        int quantity = book.getQuantity();
        int updatedQuantity = quantity - 1;
        bookService.updateQuantity(id, updatedQuantity);
    }

    /**
     * @param bookId of the book that will be updated by one
     * @throws ServiceException if something wrong during the connection with database
     */
    private void increaseBookQuantity(String bookId, BookService bookService) throws ServiceException {
        long id = Long.valueOf(bookId);
        Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            int updatedQuantity = book.getQuantity() + 1;
            bookService.updateQuantity(id, updatedQuantity);
        }
    }

}
