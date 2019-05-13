package com.epam.library.model.service.orderservice.user.search;

import com.epam.library.model.service.orderservice.user.UserOrderDisplay;

public interface FindOrderIndex {

    boolean isOrderExist(UserOrderDisplay orderDisplay, Object value);

}
