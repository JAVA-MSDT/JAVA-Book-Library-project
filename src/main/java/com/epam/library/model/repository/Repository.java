package com.epam.library.model.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void add(T item);
    void remove(long id);
    Optional<T> getById(long id);
    List<T> getAll();

}
