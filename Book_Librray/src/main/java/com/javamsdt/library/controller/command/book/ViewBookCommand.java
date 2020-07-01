package com.javamsdt.library.controller.command.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;

import java.util.Optional;

public class ViewBookCommand implements Command {
    private BookService bookService;

    public ViewBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which hold information about a specific book to display it on a separate page
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        Optional<Book> optionalBook = bookService.getById(Long.parseLong(bookId));
        if (optionalBook.isPresent()) {
            request.setAttribute(BookConstant.BOOK_ATTRIBUTE, optionalBook.get());
            page = PageLocation.VIEW_BOOK;
        } else {
            request.setAttribute(BookConstant.BOOK_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.BOOK_STORE;
        }
        return new CommandResult(page);
    }
}
