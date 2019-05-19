package com.epam.library.controller.command.administration.order;

import com.epam.library.controller.builder.OrderBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.Order;
import com.epam.library.model.service.BookService;
import com.epam.library.model.service.OrderService;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.TransactionManager;
import com.epam.library.util.constant.Operation;
import com.epam.library.util.constant.RedirectTo;
import com.epam.library.util.constant.entityconstant.OrderConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdministrationUpdateOrderCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    private final static String TRUE = "true";
    private final static String FALSE = "false";

    private OrderService orderService;
    private BookService bookService;
    private TransactionManager transactionManager;
    private OrderBuilderFromRequest builderFromRequest = new OrderBuilderFromRequest();

    public AdministrationUpdateOrderCommand(OrderService orderService, BookService bookService, TransactionManager transactionManager) {
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
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult();
        String operation;
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);
        String bookId = request.getParameter(OrderConstant.BOOK_ID);

        if (orderId != null && bookReturned.trim().equalsIgnoreCase(FALSE) && !orderId.isEmpty()) {

            Order order = builderFromRequest.buildToUpdate(request);
            try {
                orderService.update(order);
                operation = Operation.UPDATED;
            } catch (ServiceException e) {
                operation = Operation.UPDATE_FAIL;
                logger.error(e);
            }
        } else if (orderId != null && bookReturned.trim().equalsIgnoreCase(TRUE) && !orderId.isEmpty()) {

            try {
                orderService.administrationOrderRemoval(orderId, bookId, bookService, transactionManager);
                operation = Operation.REMOVED;
            } catch (ServiceException e) {
                operation = Operation.REMOVE_FAIL;
                logger.error(e);
            }
        } else {
            Order order = builderFromRequest.buildToAdd(request);
            try {
                orderService.save(order);
                operation = Operation.INSERTED;
            } catch (ServiceException e) {
                operation = Operation.INSERT_FAIL;
                logger.error(e);
            }

        }
        commandResult.redirect(RedirectTo.ADMINISTRATION_EDIT_ORDER_PAGE + Operation.OPERATION_STATUS + operation);
        return commandResult;
    }

}
