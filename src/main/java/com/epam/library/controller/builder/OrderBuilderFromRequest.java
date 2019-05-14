package com.epam.library.controller.builder;

import com.epam.library.entity.Order;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.entityconstant.OrderConstant;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class OrderBuilderFromRequest {

    /**
     * To help build the order for the update in the LibrarianUpdateOrderCommand class
     *
     * @param request to extract the order info from the form
     * @return order after extracting it's information
     */
    public Order buildToUpdate(HttpServletRequest request) {
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);

        Order order = buildToAdd(request);
        order.setOrderId(Long.valueOf(orderId));
        order.setBookReturned(Boolean.valueOf(bookReturned.trim()));

        return order;
    }

    /**
     * To help building the order for the update in the LibrarianUpdateOrderCommand class
     *
     * @param request to extract the order info from the form
     * @return order after extracting it's information
     */
    public Order buildToAdd(HttpServletRequest request) {

        String bookId = request.getParameter(OrderConstant.BOOK_ID);
        String userId = request.getParameter(OrderConstant.USER_ID);
        String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
        String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
        String readingPlace = request.getParameter(OrderConstant.READING_PLACE);

        return new Order(Long.valueOf(bookId), Long.valueOf(userId), Date.valueOf(orderDate),
                Date.valueOf(returningDate), EnumService.getReadingPlace(readingPlace));
    }

    /**
     * @param request from ConfirmOrderCommand
     * @param bookId  which the user chose it
     * @param userId  of the user that has ordered the book
     * @return order to save it
     */
    public Order userOrder(HttpServletRequest request, Long bookId, Long userId) {

        String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
        String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
        String readingPlace = request.getParameter(OrderConstant.READING_PLACE);

        return new Order(bookId, userId, Date.valueOf(orderDate),
                Date.valueOf(returningDate), EnumService.getReadingPlace(readingPlace));
    }

}
