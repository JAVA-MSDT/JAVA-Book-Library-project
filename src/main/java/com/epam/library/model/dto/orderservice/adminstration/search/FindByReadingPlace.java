package com.epam.library.model.dto.orderservice.adminstration.search;

import com.epam.library.entity.enumeration.ReadingPlace;
import com.epam.library.model.dto.orderservice.FindCriteria;
import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

public class FindByReadingPlace implements FindCriteria<AdministrationOrderDisplay> {

    private ReadingPlace readingPlace;

    public FindByReadingPlace(ReadingPlace readingPlace) {
        this.readingPlace = readingPlace;
    }

    @Override
    public boolean isExist(AdministrationOrderDisplay orderDisplay) {
        return orderDisplay.getReadingPlace().compareTo(readingPlace) == 0;
    }
}
