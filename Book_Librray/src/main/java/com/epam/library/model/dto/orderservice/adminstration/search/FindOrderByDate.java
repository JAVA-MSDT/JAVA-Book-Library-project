package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindOrderByDate implements FindCriteria<AdministrationOrderDisplay> {
    private Date orderDate;

    public FindOrderByDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {

        return orderDisplay.getOrderDate().compareTo(orderDate) == 0;
    }
}
