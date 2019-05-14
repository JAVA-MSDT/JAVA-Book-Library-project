package com.epam.library.controller.command.administration.order;

import com.epam.library.controller.builder.OrderBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.Order;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.TransactionManager;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LibrarianUpdateOrderCommand implements Command {
    private final static String TRUE = "true";
    private final static String FALSE = "false";

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
        HttpSession session = request.getSession();
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);
        String bookId = request.getParameter(OrderConstant.BOOK_ID);

        if (orderId != null && bookReturned.trim().equalsIgnoreCase(FALSE) && !orderId.isEmpty()) {

            Order order = builderFromRequest.buildToUpdate(request);
            orderService.update(order);
            request.setAttribute(DiffConstant.SUCCESS_INFO_UPDATE, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.ADMINISTRATION_EDIT_ORDER;
        } else if (orderId != null && bookReturned.trim().equalsIgnoreCase(TRUE) && !orderId.isEmpty()) {

            orderService.administrationOrderRemoval(orderId, bookId, bookService, transactionManager);
            request.setAttribute(DiffConstant.REMOVE_DONE, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.ADMINISTRATION_ORDER_LIST;
        } else {

            if (session.getAttribute(DiffConstant.ITEM_INSERTED) == null) { // to prevent double submit
                Order order = builderFromRequest.buildToAdd(request);
                orderService.save(order);
                request.setAttribute(DiffConstant.INSERT_SUCCESS, DiffConstant.READ_FROM_PROPERTIES);
                session.setAttribute(DiffConstant.ITEM_INSERTED, DiffConstant.INSERTED);
                page = PageLocation.ADMINISTRATION_EDIT_ORDER;
            }else {
                request.setAttribute(DiffConstant.DOUBLE_SUBMIT_ATTEMPT, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_EDIT_ORDER;
            }
        }
        return page;
    }

}
