package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindByBookName implements FindCriteria<AdministrationOrderDisplay> {

    private String bookName;

    public FindByBookName(String bookName) {
        this.bookName = bookName;
    }
    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getBookName().equalsIgnoreCase(bookName);
    }
}
