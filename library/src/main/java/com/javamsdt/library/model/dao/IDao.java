package com.javamsdt.library.model.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    Optional<T> getById(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(long id) throws DaoException;

    void update(T item) throws DaoException;

}
