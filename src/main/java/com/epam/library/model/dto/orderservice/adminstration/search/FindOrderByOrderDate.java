package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindOrderByOrderDate implements FindOrderCriteria {
    private Date orderDate;

    public FindOrderByOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }
    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {

        return orderDisplay.getOrderDate().compareTo(orderDate) == 0;
    }
}
