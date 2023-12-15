package by.bsuir.movieratingsystem.service;

import by.bsuir.movieratingsystem.dao.MovieDao;
import by.bsuir.movieratingsystem.dao.ReviewDao;
import by.bsuir.movieratingsystem.dao.UserDao;
import by.bsuir.movieratingsystem.entity.Movie;
import by.bsuir.movieratingsystem.entity.Review;
import by.bsuir.movieratingsystem.entity.User;
import by.bsuir.movieratingsystem.exception.DaoException;
import by.bsuir.movieratingsystem.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;
    private final UserDao userDao;
    private final MovieDao movieDao;

    public List<Review> getReviews(Long movieId, Long userId) throws ServiceException {
        try {
            return reviewDao.findReviewsByMovieId(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @SneakyThrows
    public void addReview(Long movieId, int rating, String text, Long userId) throws ServiceException {
        User user;
        try {
            user = userDao.findById(userId).get();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Review review = new Review(null, userId, user.getUsername(), user.getRole(), movieId, rating, text);
        try {
            reviewDao.save(review);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Movie movie = movieDao.findById(movieId).get();
        List<Review> reviews = reviewDao.findReviewsByMovieId(movieId);
        double movieRating = 0.0;
        for (Review r : reviews) {
            movieRating += r.getRate();
        }
        movie.setRating(movieRating/reviews.size());
        movieDao.updateMovie(movie);
    }

    @SneakyThrows
    public void deleteReview(Long reviewId, Long movieId) throws ServiceException {
        try {
            reviewDao.deleteReview(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Movie movie = movieDao.findById(movieId).get();
        List<Review> reviews = reviewDao.findReviewsByMovieId(movieId);
        double movieRating = 0.0;
        for (Review r : reviews) {
            movieRating += r.getRate();
        }
        movie.setRating(movieRating/reviews.size());
        movieDao.updateMovie(movie);
    }
}
