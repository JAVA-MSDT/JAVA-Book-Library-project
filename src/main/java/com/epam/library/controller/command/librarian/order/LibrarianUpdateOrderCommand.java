package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.builder.OrderBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.TransactionManager;
import com.epam.library.util.constant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianUpdateOrderCommand implements Command {
    private OrderService orderService;
    private BookService bookService;
    private TransactionManager transactionManager;
    private OrderBuilderFromRequest builderFromRequest = new OrderBuilderFromRequest();

    public LibrarianUpdateOrderCommand(OrderService orderService, BookService bookService, TransactionManager transactionManager) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.transactionManager = transactionManager;
    }

    /**
     * @param response to jsp
     * @param request  from jsp
     * @return page depends on if the order is it for editing or for saving, checking if the
     * bookReturned is false we will update the existing order,
     * if the bookReturned is true we will update the quantity of the book which returned by 1
     * then removing the order from the list.
     * @throws ServiceException is something  wrong happens during order update or save
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;

        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);
        String bookId = request.getParameter(OrderConstant.BOOK_ID);

        if (orderId != null && bookReturned.equalsIgnoreCase("false") && !orderId.isEmpty()) {
            Order order = builderFromRequest.buildToUpdate(request);
            orderService.update(order);
            page = PageLocation.ADMINISTRATION_EDIT_ORDER;
        } else if (orderId != null && bookReturned.equalsIgnoreCase("true")&& !orderId.isEmpty()) {
           orderService.administrationOrderRemoval(orderId, bookId, bookService, transactionManager);
            page = PageLocation.ADMINISTRATION_EDIT_ORDER;
        } else {
            Order order = builderFromRequest.buildToAdd(request);
            orderService.save(order);
            page = PageLocation.PROFILE;
        }
        return page;
    }

}
