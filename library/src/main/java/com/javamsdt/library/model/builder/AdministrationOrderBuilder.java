package com.javamsdt.library.model.builder;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.library.entity.enumeration.ReadingPlace;
import com.javamsdt.library.model.dto.orderservice.AdministrationOrderDisplay;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.entityconstant.OrderConstant;

public class AdministrationOrderBuilder implements Builder<AdministrationOrderDisplay> {
    private final static String BOOK_NAME = "book_title";
    private final static String USER_NAME = "first_name";
    private final static String USER_EMAIL = "user_email";

    @Override
    public AdministrationOrderDisplay build(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong(OrderConstant.ORDER_ID);
        String bookName = resultSet.getString(BOOK_NAME);
        String userName = resultSet.getString(USER_NAME);
        String userEmail = resultSet.getString(USER_EMAIL);
        Date orderDate = resultSet.getDate(OrderConstant.ORDER_DATE);
        Date returningDate = resultSet.getDate(OrderConstant.RETURNING_DATE);
        Array readingPlaceFromResult = resultSet.getArray(OrderConstant.READING_PLACE);
        String[] readingPlaceFromArray = (String[]) readingPlaceFromResult.getArray();
        ReadingPlace readingPlace = EnumService.getReadingPlace(readingPlaceFromArray[0]);
        boolean bookReturned = resultSet.getBoolean(OrderConstant.BOOK_RETURNED);

        return new AdministrationOrderDisplay(orderId, bookName, userName, userEmail, orderDate, returningDate, readingPlace, bookReturned);
    }
}
