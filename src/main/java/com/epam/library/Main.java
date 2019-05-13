package com.epam.library;


import com.epam.library.entity.Order;
import com.epam.library.util.EnumService;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        String bookId = "15";
        String userId = "15";
        String orderDate = "2019-05-08";
        String returningDate = "2019-05-11";
        String readingPlace = "Home";
        Order order = obj(bookId, userId, orderDate, returningDate, readingPlace);
        System.out.println(order);
    }

    static Order obj(String bookId, String userId, String orderDate, String returningDate, String readerPlace) {

        return new Order(Long.valueOf(bookId), Long.valueOf(userId), Date.valueOf(orderDate),
                Date.valueOf(returningDate), EnumService.getReadingPlace(readerPlace));
    }
}
