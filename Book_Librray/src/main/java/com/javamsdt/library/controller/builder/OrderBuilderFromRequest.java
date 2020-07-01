package com.javamsdt.library.controller.builder;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.library.entity.Order;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;

import java.sql.Date;

public class OrderBuilderFromRequest {

    /**
     * To help build the order for the update in the AdministrationUpdateOrderCommand class
     *
     * @param request to extract the order info from the form
     * @return order after extracting it's information
     */
    public Order buildToUpdate(HttpServletRequest request) {
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);

        Order order = buildToAdd(request);
        order.setOrderId(Long.parseLong(orderId));
        order.setBookReturned(Boolean.parseBoolean(bookReturned.trim()));

        return order;
    }

    /**
     * To help building the order for the update in the AdministrationUpdateOrderCommand class
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

        return new Order(Long.parseLong(bookId), Long.parseLong(userId), Date.valueOf(orderDate),
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
