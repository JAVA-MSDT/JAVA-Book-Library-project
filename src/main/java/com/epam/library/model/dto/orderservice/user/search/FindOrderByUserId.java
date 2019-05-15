package com.epam.library.model.dto.orderservice.user.search;

import com.epam.library.model.dto.orderservice.user.UserOrderDisplay;

public class FindOrderByUserId implements FindOrderIndex {


    @Override
    public boolean isOrderExist(UserOrderDisplay orderDisplay, Object value) {
        return orderDisplay.getUserId() == (Long) value;
    }
}
