package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindOrderByReturningDate implements FindOrderIndex {

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {

        return orderDisplay.getReturningDate().compareTo(Date.valueOf((String) value)) == 0;
    }
}
