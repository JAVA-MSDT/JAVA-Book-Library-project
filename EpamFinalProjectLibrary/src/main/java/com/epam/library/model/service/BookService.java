package com.epam.library.model.service;

import com.epam.library.entity.Book;
import com.epam.library.model.dao.BookDao;
import com.epam.library.model.dao.DaoException;
import com.epam.library.util.validate.ArgumentValidator;

import java.util.List;
import java.util.Optional;

public class BookService implements Service<Book> {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Optional<Book> getById(long id) throws ServiceException {
        try {
            return bookDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in getById at bookService class", e);
        }
    }

    public List<Book> getAll() throws ServiceException {
        try {
            return bookDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("exception in findAllBook at bookService class", e);
        }
    }

    public void save(Book book) throws ServiceException {
        ArgumentValidator.checkForNull(book, "Not allow for a null book in save at bookService class");
        try {
            bookDao.save(book);
        } catch (DaoException e) {
            throw new ServiceException("exception in save at bookService class", e);
        }
    }

    public void removeById(long id) throws ServiceException {
        try {
            bookDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in save at bookService class", e);
        }
    }

    public void update(Book book) throws ServiceException {
        ArgumentValidator.checkForNull(book, "Not allow for a null book in update at bookService class");
        try {
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException("exception in update at bookService class", e);
        }
    }

    public void updateQuantity(Long bookId, int quantity) throws ServiceException {
        try {
            bookDao.updateQuantity(bookId, quantity);
        } catch (DaoException e) {
            throw new ServiceException("exception in updateQuantity at bookService class", e);
        }
    }

}
