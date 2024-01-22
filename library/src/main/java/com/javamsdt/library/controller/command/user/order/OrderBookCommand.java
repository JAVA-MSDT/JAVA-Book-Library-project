package com.javamsdt.library.controller.command.user.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.Book;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.BookService;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.BookConstant;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.Optional;

public class OrderBookCommand implements Command {
    private BookService bookService;

    public OrderBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which view the specified book from the book store to order it.
     * if the user is login so he is not null, we will display another form to complete the
     * needed fields for the order,
     * if the user is null we will display a message that he is not in the system.
     * @throws ServiceException if something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = null;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String bookId = request.getParameter(BookConstant.BOOK_ID);
        Optional<Book> optionalBook = bookService.getById(Long.parseLong(bookId));

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (user != null) {
                request.setAttribute(BookConstant.BOOK_ATTRIBUTE, book);
                request.setAttribute(DiffConstant.DISPLAY, DiffConstant.CONFIRM);
                page = PageLocation.VIEW_BOOK;
            } else {
                request.setAttribute(BookConstant.BOOK_ATTRIBUTE, book);
                request.setAttribute(UserConstant.INVALID_LOGIN, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.VIEW_BOOK;
            }
        }

        return new CommandResult(page);
    }


}
