package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindOrderByReturningDate implements FindOrderCriteria {

    private Date returningDate;

    public FindOrderByReturningDate(Date returningDate){
        this.returningDate = returningDate;
    }

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {

        return orderDisplay.getReturningDate().compareTo(returningDate) == 0;
    }
}
