package com.epam.library.model.service.orderservice.adminstration.search;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

public interface FindOrderIndex {

    boolean isOrderExist(AdministrationOrderDisplay orderDisplay, Object value);
}
