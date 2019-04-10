package com.epam.library.model.repository.specification;

public interface Specification<T> {
    boolean specify(T item);
}
