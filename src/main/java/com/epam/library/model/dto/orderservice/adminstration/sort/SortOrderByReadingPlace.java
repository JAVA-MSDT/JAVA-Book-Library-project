package com.epam.library.model.dto.orderservice.adminstration.sort;

import com.epam.library.model.dto.orderservice.adminstration.AdministrationOrderDisplay;

import java.util.Comparator;

public class SortOrderByReadingPlace implements Comparator<AdministrationOrderDisplay> {

    @Override
    public int compare(AdministrationOrderDisplay o1, AdministrationOrderDisplay o2) {
        return o1.getReadingPlace().compareTo(o2.getReadingPlace());
    }
}
