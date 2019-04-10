package com.epam.library.model.builder;

import com.epam.library.entity.Order;
import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.util.validate.ArgumentValidator;
import com.epam.library.util.EnumService;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements Builder<Order> {

    /**
     *
     * @param resultSet which has the info of the order
     * @return order after extracting the date from the data base.
     * @throws SQLException
     */
    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in OrderBuilder");

            int orderId = resultSet.getInt("id");
            int bookId = resultSet.getInt("book_id");
            int userId = resultSet.getInt("user_id");
            Date orderDate = resultSet.getDate("order_date");
            Date returningDate = resultSet.getDate("returning_date");
            ReadingPlace readingPlace = EnumService.getReadingPlace(resultSet.getString("reading_place"));
            boolean bookReturned = resultSet.getBoolean("book_returned");

        return  new Order(orderId, bookId, userId, orderDate, returningDate, readingPlace, bookReturned);
    }
}
