package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindOrderByBookName implements FindOrderCriteria {

    private String bookName;

    public FindOrderByBookName(String bookName){
        this.bookName = bookName;
    }
    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getBookName().equalsIgnoreCase(bookName);
    }
}
