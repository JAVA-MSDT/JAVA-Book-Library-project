package com.epam.library.model.builder;

import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.model.dto.orderservice.user.UserOrderDisplay;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.entityconstant.OrderConstant;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderBuilder implements Builder<UserOrderDisplay> {
    private final static String BOOK_NAME = "book.name";

    @Override
    public UserOrderDisplay build(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong(OrderConstant.ORDER_ID);
        long userId = resultSet.getLong(OrderConstant.USER_ID);
        String bookName = resultSet.getString(BOOK_NAME);
        Date orderDate = resultSet.getDate(OrderConstant.ORDER_DATE);
        Date returningDate = resultSet.getDate(OrderConstant.RETURNING_DATE);
        ReadingPlace readingPlace = EnumService.getReadingPlace(resultSet.getString(OrderConstant.READING_PLACE));
        boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);
        return new UserOrderDisplay(orderId, userId, bookName, orderDate, returningDate, readingPlace, bookReturned);
    }
}
