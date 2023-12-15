package by.bsuir.movieratingsystem.dao;

import by.bsuir.movieratingsystem.entity.Movie;
import by.bsuir.movieratingsystem.exception.DaoException;

public interface MovieDao extends Dao<Movie> {

    void updateMovie(Movie movie) throws DaoException;
}
