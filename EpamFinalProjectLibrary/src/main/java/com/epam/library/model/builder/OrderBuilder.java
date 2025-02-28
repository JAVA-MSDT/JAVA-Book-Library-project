package com.epam.library.model.builder;

import com.epam.library.entity.Order;
import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.OrderConstant;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements Builder<Order> {

    /**
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

        return new Order(orderId, bookId, userId, orderDate, returningDate, readingPlace, bookReturned);
    }

}
