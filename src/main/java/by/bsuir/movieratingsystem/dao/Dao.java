package by.bsuir.movieratingsystem.dao;

import by.bsuir.movieratingsystem.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id) throws DaoException;

    void save(T item) throws DaoException;

    List<T> findAll() throws DaoException;

}
