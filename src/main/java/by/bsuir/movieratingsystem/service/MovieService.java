package by.bsuir.movieratingsystem.service;

import by.bsuir.movieratingsystem.dao.MovieDao;
import by.bsuir.movieratingsystem.entity.Movie;
import by.bsuir.movieratingsystem.exception.DaoException;
import by.bsuir.movieratingsystem.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieDao movieDao;

    public Object getMoviesList() throws ServiceException {
        try {
            return movieDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addMovie(String title, String description, String imgUrl) throws ServiceException {
        Movie movie = new Movie(null, title, description, 0.0, imgUrl);
        try {
            movieDao.save(movie);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void editMovie(String title, String description, String imgUrl, Long movieId) {
        Movie movie = new Movie(movieId, title, description, -0.1, imgUrl);
        try {
            movieDao.updateMovie(movie);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie getMovie(Long id) {
        try {
            return movieDao.findById(id).get();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
