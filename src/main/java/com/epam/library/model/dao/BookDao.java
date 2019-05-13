package com.epam.library.model.dao;

import com.epam.library.entity.Book;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.model.dao.query.BookQuery;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class BookDao extends AbstractDao<Book> {


    public BookDao(Connection connection) {
        super(connection);
    }

    public BookDao() {

    }

    @Override
    public Optional<Book> getById(long id) throws DaoException {
        return executeSingleResponseQuery(BookQuery.SELECT_BOOK_BY_ID, new BookBuilder(), String.valueOf(id));
    }

    @Override
    public List<Book> getAll() throws DaoException {
        return executeQuery(BookQuery.SELECT_ALL_BOOKS, new BookBuilder());
    }

    @Override
    public void save(Book item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at BookDao class");

        String[] bookInfo = {item.getName(), String.valueOf(item.getQuantity())};
        executeUpdate(BookQuery.INSERT_BOOK, bookInfo);


    }

    @Override
    public void removeById(long id) throws DaoException {
        executeUpdate(BookQuery.DELETE_BOOK, String.valueOf(id));
    }

    @Override
    public void update(Book item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at BookDao class");

        String[] bookInfo = {item.getName(), String.valueOf(item.getQuantity()), String.valueOf(item.getId())};
        executeUpdate(BookQuery.UPDATE_BOOK, bookInfo);
    }

    public void updateQuantity(Long bookId, int quantity) throws DaoException {
        executeUpdate(BookQuery.UPDATE_BOOK_QUANTITY, String.valueOf(quantity), String.valueOf(bookId));
    }

    public List<Book> findByName(String name) throws DaoException {
        return executeQuery(BookQuery.SELECT_BOOK_BY_NAME, new BookBuilder(), name);
    }

    // Sorting
    public List<Book> sortBooksByName() throws DaoException {
        return executeQuery(BookQuery.SELECT_ALL_BOOKS_SORTED_BY_NAME, new BookBuilder());
    }

    public List<Book> sortBookByQuantity() throws DaoException {
        return executeQuery(BookQuery.SELECT_ALL_BOOKS_SORTED_BY_QUANTITY, new BookBuilder());
    }

}
