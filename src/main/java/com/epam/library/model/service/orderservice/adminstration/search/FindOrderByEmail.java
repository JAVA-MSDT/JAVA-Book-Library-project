package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;


public class FindOrderByEmail implements FindOrderIndex {

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {
        return orderDisplay.getUserEmail().equalsIgnoreCase((String) value);
    }
}
