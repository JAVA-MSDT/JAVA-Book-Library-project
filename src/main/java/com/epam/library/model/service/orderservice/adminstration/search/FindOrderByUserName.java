package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

public class FindOrderByUserName implements FindOrderIndex {

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {
        return orderDisplay.getUserName().equalsIgnoreCase((String) value);
    }
}
