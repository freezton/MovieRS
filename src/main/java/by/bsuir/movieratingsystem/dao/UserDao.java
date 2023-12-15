package by.bsuir.movieratingsystem.dao;

import by.bsuir.movieratingsystem.entity.User;
import by.bsuir.movieratingsystem.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByUsername(String username) throws DaoException;

    void updateUser(User user) throws DaoException;
}
