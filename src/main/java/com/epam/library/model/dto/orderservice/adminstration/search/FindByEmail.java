package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;


public class FindByEmail implements FindCriteria<AdministrationOrderDisplay> {

    private String email;

    public FindByEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getUserEmail().equalsIgnoreCase(email);
    }
}
