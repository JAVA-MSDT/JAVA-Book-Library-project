package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.model.dao.BookDao;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.DaoFactory;
import com.epam.library.util.PageLocation;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Book Store in jsp page which holds a table with all the books inside the database.
 */
public class LibrarianBookStore implements Command {
    private BookDao bookDao = DaoFactory.getInstance().getBookDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
            if(user != null){
                request.setAttribute(UserConstant.USER_ATTRIBUTE, user);
                List<Book> books = bookDao.getAll();
                request.setAttribute(BookConstant.BOOK_LIST, books);
                page = PageLocation.LIBRARIAN_BOOK_STORE;
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
        return page;
    }


}
