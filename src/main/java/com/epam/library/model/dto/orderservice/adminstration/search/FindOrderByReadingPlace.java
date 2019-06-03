package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindOrderByReadingPlace implements FindOrderCriteria {

    private ReadingPlace readingPlace;

    public FindOrderByReadingPlace(ReadingPlace readingPlace){
        this.readingPlace = readingPlace;
    }

    @Override
    public boolean isOrderExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getReadingPlace().compareTo(readingPlace) == 0;
    }
}
