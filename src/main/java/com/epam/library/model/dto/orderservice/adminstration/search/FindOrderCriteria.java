package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public interface FindOrderCriteria {

    boolean isOrderExist(AdministrationOrderDisplay orderDisplay);
}
