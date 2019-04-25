package com.epam.library.model.builder;

import com.epam.library.entity.Book;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.validate.ArgumentValidator;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * To help build the book for the update in the LibrarianUpdateUser class
     * @param request to extract the book info from a form
     * @return  book after extracting it's information
     */
    public Book buildBookToUpdate(HttpServletRequest request){
        String id = request.getParameter(BookConstant.BOOK_ID);
        String name = request.getParameter(BookConstant.BOOK_NAME);
        String quantity = request.getParameter(BookConstant.BOOK_QUANTITY);

        return new Book(Long.valueOf(id), name, Integer.valueOf(quantity));
    }

    /**
     * To help building the book for the update in the LibrarianUpdateUser class
     * @param request to extract the book info from a form to add it in the database
     * @return  book after extracting it's information
     */
    public Book buildBookToAdd(HttpServletRequest request){

        String name = request.getParameter(BookConstant.BOOK_NAME);
        String quantity = request.getParameter(BookConstant.BOOK_QUANTITY);

        return new Book(name, Integer.valueOf(quantity));
    }
}
