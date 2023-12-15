package by.bsuir.movieratingsystem.dao;

import by.bsuir.movieratingsystem.entity.Review;
import by.bsuir.movieratingsystem.exception.DaoException;

import java.util.List;

public interface ReviewDao extends Dao<Review> {

    List<Review> getReviews(Long movieId, Long userId) throws DaoException;

    List<Review> findReviewsByMovieId(Long movieId) throws DaoException;

    void deleteReview(Long reviewId) throws DaoException;
}
