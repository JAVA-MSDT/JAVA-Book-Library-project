package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindByUserName implements FindCriteria<AdministrationOrderDisplay> {

    private String userName;

    public FindByUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getUserName().equalsIgnoreCase(userName);
    }
}
