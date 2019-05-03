package com.epam.library.controller.command.reader.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class OrderBookCommand implements Command {
    private BookService bookService;

    public OrderBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
       String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        Optional<Book> optionalBook= bookService.findById(Long.valueOf(bookId));
        if(user != null){
            request.setAttribute(BookConstant.BOOK_ATTRIBUTE, optionalBook.get());
            request.setAttribute(DiffConstant.DISPLAY, DiffConstant.CONFIRM);
            page = PageLocation.VIEW_BOOK;
        }else {
            request.setAttribute(BookConstant.BOOK_ATTRIBUTE, optionalBook.get());
            request.setAttribute(UserConstant.INVALID_LOGIN, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.VIEW_BOOK;
        }
        return page;
    }
}
