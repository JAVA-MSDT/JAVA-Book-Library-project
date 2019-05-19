package com.epam.library.controller.command.administration.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.BookConstant;
import com.epam.library.util.constant.entityconstant.OrderConstant;
import com.epam.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class AdministrationEditOrderCommand implements Command {

    private final static String USER_EMAIL = "userEmail";
    private final static String BOOK_NAME = "bookName";


    private OrderService orderService;
    private BookService bookService;
    private UserService userService;

    public AdministrationEditOrderCommand(OrderService orderService, BookService bookService, UserService userService) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.userService = userService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds a form that has the information about a specific order to be edited
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String orderId = request.getParameter(OrderConstant.ORDER_ID);

        List<Book> bookList = bookService.getAll();
        List<User> userList = userService.getAll();
        if (orderId != null) {
            Optional<Order> optionalOrder = orderService.getById(Long.valueOf(orderId));
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                request.setAttribute(OrderConstant.EDIT_ORDER, order);
                request.setAttribute(BookConstant.BOOK_LIST, bookList);
                request.setAttribute(UserConstant.USER_LIST, userList);
                page = PageLocation.ADMINISTRATION_EDIT_ORDER;
            } else {
                request.setAttribute(OrderConstant.ORDER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_ORDER_LIST;
            }
        } else {
            request.setAttribute(BookConstant.BOOK_LIST, bookList);
            request.setAttribute(UserConstant.USER_LIST, userList);
            page = PageLocation.ADMINISTRATION_EDIT_ORDER;
        }

        return new CommandResult(page);
    }


}
