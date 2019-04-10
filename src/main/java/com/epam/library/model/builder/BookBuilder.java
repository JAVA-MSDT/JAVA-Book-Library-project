package com.epam.library.model.builder;

import com.epam.library.entity.Book;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {

    /**
     *
     * @param resultSet which has the info of the book
     * @return book after extracting the date from the data base.
     * @throws SQLException
     */
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in BookBuilder");

            int id = resultSet.getInt("id");
            String bookName = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");

        return new Book(id, bookName, quantity);
    }
}
