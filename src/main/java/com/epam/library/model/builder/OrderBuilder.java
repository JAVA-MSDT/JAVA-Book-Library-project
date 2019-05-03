package com.epam.library.model.builder;

import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.validate.ArgumentValidator;
import com.epam.library.util.EnumService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements Builder<Order> {

    /**
     *
     * @param resultSet which has the info of the order
     * @return order after extracting the date from the data base.
     * @throws SQLException if something wrong happens during the query
     */
    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in OrderBuilder");

            int orderId = resultSet.getInt(OrderConstant.ORDER_ID);
            int bookId = resultSet.getInt(OrderConstant.BOOK_ID);
            int userId = resultSet.getInt(OrderConstant.USER_ID);
            Date orderDate = resultSet.getDate(OrderConstant.ORDER_DATE);
            Date returningDate = resultSet.getDate(OrderConstant.RETURNING_DATE);
            ReadingPlace readingPlace = EnumService.getReadingPlace(resultSet.getString(OrderConstant.READING_PLACE));
            boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);

        return  new Order(orderId, bookId, userId, orderDate, returningDate, readingPlace, bookReturned);
    }


    /**
     * To help build the order for the update in the LibrarianUpdateOrderCommand class
     * @param request to extract the order info from the form
     * @return  order after extracting it's information
     */
    public Order buildToUpdate(HttpServletRequest request){
        String orderId = request.getParameter(OrderConstant.ORDER_ID);
        String bookReturned = request.getParameter(OrderConstant.BOOK_RETURNED);

        Order order = buildToAdd(request);
        order.setOrderId(Long.valueOf(orderId));
        order.setBookReturned(getBooleanValue(bookReturned));

        return order;
    }

    /**
     * To help building the order for the update in the LibrarianUpdateOrderCommand class
     * @param request to extract the order info from the form
     * @return  order after extracting it's information
     */
    public Order buildToAdd(HttpServletRequest request){
        String bookId = request.getParameter(OrderConstant.BOOK_ID);
        String userId = request.getParameter(OrderConstant.USER_ID);
        String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
        String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
        String readingPlace = request.getParameter(OrderConstant.READING_PLACE);

        return new Order(Long.valueOf(bookId), Long.valueOf(userId), Date.valueOf(orderDate),
                Date.valueOf(returningDate), EnumService.getReadingPlace(readingPlace));
    }

    /**
     *
     * @param request from ConfirmOrderCommand
     * @param book to extract it is id
     * @param user to extract it is id
     * @return order to save it
     */
    public Order readerOrder(HttpServletRequest request, Book book, User user){
        String bookId = String.valueOf(book.getId());
        String userId = String.valueOf(user.getId());
        String orderDate = request.getParameter(OrderConstant.ORDER_DATE);
        String returningDate = request.getParameter(OrderConstant.RETURNING_DATE);
        String readingPlace = request.getParameter(OrderConstant.READING_PLACE);

        return new Order(Long.valueOf(bookId), Long.valueOf(userId), Date.valueOf(orderDate),
                Date.valueOf(returningDate), EnumService.getReadingPlace(readingPlace));
    }

    /**
     *
     * @param booleanHolder string with a value true or false
     * @return boolean value depends on the value of the booleanHolder string
     */
    private boolean getBooleanValue(String booleanHolder) {
        return booleanHolder.equalsIgnoreCase("true");
    }
}
