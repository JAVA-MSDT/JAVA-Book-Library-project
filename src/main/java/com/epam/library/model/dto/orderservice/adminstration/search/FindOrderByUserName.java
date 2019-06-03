package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindOrderByUserName implements FindOrderCriteria {

    private String userName;
    public FindOrderByUserName(String userName){
        this.userName = userName;
    }
    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getUserName().equalsIgnoreCase(userName);
    }
}
