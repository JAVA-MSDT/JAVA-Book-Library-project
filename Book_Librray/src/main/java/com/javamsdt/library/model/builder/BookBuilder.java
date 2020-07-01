package com.javamsdt.library.model.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.library.entity.Book;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.validate.ArgumentValidator;

public class BookBuilder implements Builder<Book> {

    /**
     * @param resultSet which has the info of the book
     * @return book after extracting the date from the data base.
     * @throws SQLException if something wrong happens during the building
     */
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in BookBuilder");

        long id = resultSet.getLong(BookConstant.BOOK_ID);
        String bookName = resultSet.getString(BookConstant.BOOK_NAME);
        int quantity = resultSet.getInt(BookConstant.BOOK_QUANTITY);

        return new Book(id, bookName, quantity);
    }

}
