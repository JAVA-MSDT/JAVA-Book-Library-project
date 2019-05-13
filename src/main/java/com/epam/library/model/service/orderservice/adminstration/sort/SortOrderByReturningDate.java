package com.epam.library.model.service.orderservice.adminstration.sort;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

import java.util.Comparator;

public class SortOrderByReturningDate implements Comparator<AdministrationOrderDisplay> {
    @Override
    public int compare(AdministrationOrderDisplay o1, AdministrationOrderDisplay o2) {
        return o1.getReturningDate().compareTo(o2.getReturningDate());
    }
}
