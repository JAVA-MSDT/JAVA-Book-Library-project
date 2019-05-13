package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

public class FindOrderByBookName implements FindOrderIndex {
    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {
        return orderDisplay.getBookName().equalsIgnoreCase((String) value);
    }
}
