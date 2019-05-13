package com.epam.library.model.service.orderservice.adminstration.sort;

import com.epam.library.model.service.orderservice.adminstration.AdministrationOrderDisplay;

import java.util.Comparator;

public class SortOrderByOrderDate implements Comparator<AdministrationOrderDisplay> {
    @Override
    public int compare(AdministrationOrderDisplay o1, AdministrationOrderDisplay o2) {
        return o1.getOrderDate().compareTo(o2.getOrderDate());
    }
}
