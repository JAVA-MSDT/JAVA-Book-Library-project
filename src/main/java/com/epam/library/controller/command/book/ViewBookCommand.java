package com.epam.library.controller.command.book;

import com.epam.library.controller.command.Command;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.entityconstant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        Optional<Book> optionalBook = bookService.getById(Long.valueOf(bookId));
        if (optionalBook.isPresent()) {
            request.setAttribute(BookConstant.BOOK_ATTRIBUTE, optionalBook.get());
            return PageLocation.VIEW_BOOK;
        }
        return PageLocation.BOOK_STORE;
    }
}
