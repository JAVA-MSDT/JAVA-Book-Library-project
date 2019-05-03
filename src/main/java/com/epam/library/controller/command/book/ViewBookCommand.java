package com.epam.library.controller.command.book;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ViewBookCommand implements Command {
    private BookService bookService;

    public ViewBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        Optional<Book> optionalBook= bookService.findById(Long.valueOf(bookId));
        if(optionalBook.isPresent()){
            request.setAttribute(BookConstant.BOOK_ATTRIBUTE, optionalBook.get());
            return PageLocation.VIEW_BOOK;
        }
        return PageLocation.BOOK_STORE;
    }
}
