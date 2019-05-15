package com.epam.library.model.dto.orderservice.user.search;

import com.epam.library.model.dto.orderservice.user.UserOrderDisplay;

public interface FindOrderIndex {

    boolean isOrderExist(UserOrderDisplay orderDisplay, Object value);

}
