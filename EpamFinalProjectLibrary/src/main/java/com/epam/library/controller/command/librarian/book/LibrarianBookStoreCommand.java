package com.epam.library.controller.command.librarian.book;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Book Store in jsp page which holds a table with all the books inside the database.
 */
public class LibrarianBookStoreCommand implements Command {
    private BookService bookService;

    public LibrarianBookStoreCommand(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the information about the books to display them on the page, for the
     * librarian to control them, adding or editing
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        List<Book> books = bookService.getAll();
        request.setAttribute(BookConstant.BOOK_LIST, books);
        return PageLocation.LIBRARIAN_BOOK_STORE;

    }


}
