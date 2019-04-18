package com.epam.library.model.dao;

import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.model.builder.BookBuilder;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.dao.query.UserQuery;
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


    public BookDao(Connection connection){
        super(connection);
    }

    public BookDao(){

    }
    @Override
    public Optional<Book> getById(long id) throws DaoException {
        return executeSingleResponseQuery(BookQuery.SELECT_BOOK_BY_ID, new BookBuilder(),String.valueOf(id));
    }

    @Override
    public List<Book> getAll() throws DaoException {
        return executeQuery(BookQuery.SELECT_ALL_BOOKS, new BookBuilder());
    }

    @Override
    public void save(Book item) throws DaoException {
        ArgumentValidator.checkForNull(item, "Not allow for a null item in save at BookDao class");

        Optional<Book> book = executeSingleResponseQuery(BookQuery.SELECT_BOOK_BY_ID, new BookBuilder(),String.valueOf(item.getId()));
        String[] bookInfo = {item.getName(), String.valueOf(item.getQuantity())};
        if(!book.isPresent()){
            executeUpdate(BookQuery.INSERT_BOOK, bookInfo);
        }

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
}
