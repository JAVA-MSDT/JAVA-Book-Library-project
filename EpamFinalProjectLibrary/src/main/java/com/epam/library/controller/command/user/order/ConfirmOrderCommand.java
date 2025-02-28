package com.epam.library.controller.command.user.order;

import com.epam.library.controller.builder.OrderBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.TransactionManager;
import com.epam.library.util.constant.BookConstant;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmOrderCommand implements Command {
    private BookService bookService;
    private OrderService orderService;
    private TransactionManager transactionManager;
    private OrderBuilderFromRequest builderFromRequest = new OrderBuilderFromRequest();

    public ConfirmOrderCommand(BookService bookService, OrderService orderService, TransactionManager transactionManager) {
        this.bookService = bookService;
        this.orderService = orderService;
        this.transactionManager = transactionManager;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which confirms that order is done depends on the userSession in the library
     * using the book and user info to build order from the request.
     * @throws ServiceException if something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = null;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        String bookId = request.getParameter(BookConstant.BOOK_ID);

        if (user != null) {
            Optional<Book> optionalBook = bookService.getById(Long.valueOf(bookId));
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                Order order = builderFromRequest.userOrder(request, book.getId(), user.getId());
                orderService.confirmUserOrder(order, book, bookService, transactionManager);
                request.setAttribute(BookConstant.BOOK_ATTRIBUTE, book);
                request.setAttribute(OrderConstant.ORDER_DONE, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.VIEW_BOOK;
            } else {
                request.setAttribute(BookConstant.UNREACHABLE_BOOK, DiffConstant.READ_FROM_PROPERTIES);
            }
        }
        return page;
    }

}
