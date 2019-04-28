package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Book Store in jsp page which holds a table with all the books inside the database.
 */
public class LibrarianBookStore implements Command {
    private BookService bookService;

    public LibrarianBookStore(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = null;

            User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
            if(user != null){
                List<Book> books = bookService.getAll();
                request.setAttribute(BookConstant.BOOK_LIST, books);
                page = PageLocation.LIBRARIAN_BOOK_STORE;
            }


        return page;
    }


}
