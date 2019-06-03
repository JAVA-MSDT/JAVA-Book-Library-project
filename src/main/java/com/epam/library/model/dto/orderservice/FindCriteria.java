package com.epam.library.model.dto.orderservice;

public interface FindCriteria<T> {

    boolean isExist(T t);
}
