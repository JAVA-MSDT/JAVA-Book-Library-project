package com.epam.library.model.builder;

import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.entityconstant.OrderConstant;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministrationOrderBuilder implements Builder<AdministrationOrderDisplay> {
    private final static String BOOK_NAME = "book.name";
    private final static String USER_NAME = "user_table.name";
    private final static String USER_EMAIL = "email";

    @Override
    public AdministrationOrderDisplay build(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong(OrderConstant.ORDER_ID);
        String bookName = resultSet.getString(BOOK_NAME);
        String userName = resultSet.getString(USER_NAME);
        String userEmail = resultSet.getString(USER_EMAIL);
        Date orderDate = resultSet.getDate(OrderConstant.ORDER_DATE);
        Date returningDate = resultSet.getDate(OrderConstant.RETURNING_DATE);
        ReadingPlace readingPlace = EnumService.getReadingPlace(resultSet.getString(OrderConstant.READING_PLACE));
        boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);

        return new AdministrationOrderDisplay(orderId, bookName, userName, userEmail, orderDate, returningDate, readingPlace, bookReturned);
    }
}
