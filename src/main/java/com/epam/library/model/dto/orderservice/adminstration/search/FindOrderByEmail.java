package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;


public class FindOrderByEmail implements FindOrderCriteria {

    private String email;

    public FindOrderByEmail(String email){
        this.email = email;
    }

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getUserEmail().equalsIgnoreCase(email);
    }
}
