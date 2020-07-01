package com.javamsdt.library.controller.command.administration.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;

import java.util.List;

/**
 * Book Store in jsp page which holds a table with all the books inside the database.
 */
public class AdministrationBookStoreCommand implements Command {
    private BookService bookService;

    public AdministrationBookStoreCommand(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the information about the books to display them on the page, for the
     * administration to control them, adding or editing
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Book> books = bookService.getAll();
        request.setAttribute(BookConstant.BOOK_LIST, books);
        return new CommandResult(PageLocation.ADMINISTRATION_BOOK_STORE);

    }


}
