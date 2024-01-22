package com.javamsdt.library.model.builder;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.model.dto.orderservice.UserOrderDisplay;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;

public class UserOrderBuilder implements Builder<UserOrderDisplay> {
    private final static String BOOK_NAME = "book_title";

    @Override
    public UserOrderDisplay build(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong(OrderConstant.ORDER_ID);
        long userId = resultSet.getLong(OrderConstant.USER_ID);
        String bookName = resultSet.getString(BOOK_NAME);
        Date orderDate = resultSet.getDate(OrderConstant.ORDER_DATE);
        Date returningDate = resultSet.getDate(OrderConstant.RETURNING_DATE);
        Array readingPlaceFromResult = resultSet.getArray(OrderConstant.READING_PLACE);
        String[] readingPlaceFromArray = (String[]) readingPlaceFromResult.getArray();
        ReadingPlace readingPlace = EnumService.getReadingPlace(readingPlaceFromArray[0]);
        boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);
        return new UserOrderDisplay(orderId, userId, bookName, orderDate, returningDate, readingPlace, bookReturned);
    }
}
