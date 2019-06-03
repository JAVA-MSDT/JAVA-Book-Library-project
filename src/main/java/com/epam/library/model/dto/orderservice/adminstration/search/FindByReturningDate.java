package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.sql.Date;

public class FindByReturningDate implements FindCriteria<AdministrationOrderDisplay> {

    private Date returningDate;

    public FindByReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {

        return orderDisplay.getReturningDate().compareTo(returningDate) == 0;
    }
}
