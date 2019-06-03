package com.epam.library.model.dto.orderservice.user.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.user.UserOrderDisplay;

public class FindOrderByUserId implements FindCriteria<UserOrderDisplay> {

    private long userId;

    public FindOrderByUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public boolean isExist(UserOrderDisplay orderDisplay) {
        return orderDisplay.getUserId() == userId;
    }
}
