package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;
import com.epam.library.util.EnumService;

public class FindOrderByReadingPlace implements FindOrderIndex {

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value) {
        return orderDisplay.getReadingPlace().compareTo(EnumService.getReadingPlace((String) value)) == 0;
    }
}
