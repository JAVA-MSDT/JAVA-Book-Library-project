package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindOrderByOrderDate implements FindOrderIndex {
    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {

        return orderDisplay.getOrderDate().compareTo(Date.valueOf((String) value)) == 0;
    }
}
