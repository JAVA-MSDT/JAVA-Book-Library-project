package com.javamsdt.library.model.builder;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.library.entity.Order;
import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;
import com.javamsdt.library.util.validate.ArgumentValidator;

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
        Array placeFromResult = resultSet.getArray(OrderConstant.READING_PLACE);
        String[] readingPlaceFromArray = (String[]) placeFromResult.getArray();
        ReadingPlace readingPlace = EnumService.getReadingPlace(readingPlaceFromArray[0]);
        boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);

        return new Order(orderId, bookId, userId, orderDate, returningDate, readingPlace, bookReturned);
    }

}
