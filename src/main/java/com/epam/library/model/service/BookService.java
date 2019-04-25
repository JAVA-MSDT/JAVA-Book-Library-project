package com.epam.library.model.service;

import com.epam.library.entity.Book;
import com.epam.library.model.dao.BookDao;
import com.epam.library.model.dao.DaoException;
import com.epam.library.util.validate.ArgumentValidator;

import java.util.List;
import java.util.Optional;

public class BookService implements Service<Book>{
    private BookDao bookDao;

    public BookService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public Optional<Book> findById(long id) throws ServiceException {
        try {
            return bookDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in getById at bookService class", e);
        }
    }

    public List<Book> findAllBooks() throws ServiceException {
        try {
            return bookDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("exception in findAllBook at bookService class", e);
        }
    }

    public void saveBook(Book book) throws ServiceException {
        ArgumentValidator.checkForNull(book, "Not allow for a null book in saveBook at bookService class");
        try {
            bookDao.save(book);
        } catch (DaoException e) {
            throw new ServiceException("exception in saveBook at bookService class", e);
        }
    }

    public void removeById(long id) throws ServiceException {
        try {
            bookDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException("exception in saveBook at bookService class", e);
        }
    }

    public void updateBook(Book book) throws ServiceException {
        ArgumentValidator.checkForNull(book, "Not allow for a null book in updateBook at bookService class");
        try {
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException("exception in updateBook at bookService class", e);
        }
    }

}
