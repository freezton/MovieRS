package by.bsuir.movieratingsystem.dao.impl;

import by.bsuir.movieratingsystem.dao.MovieDao;
import by.bsuir.movieratingsystem.dao.ReviewDao;
import by.bsuir.movieratingsystem.dao.UserDao;
import by.bsuir.movieratingsystem.entity.Review;
import by.bsuir.movieratingsystem.exception.DaoException;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {

    private final SessionFactory sessionFactory;
    private final UserDao userDao;
    private final MovieDao movieDao;
    
    @Override
    public Optional<Review> findById(long id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Review> query = session.createQuery("FROM Review WHERE id = :id", Review.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Review item) throws DaoException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Review> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Review> query = session.createQuery("FROM Review", Review.class);
            return query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Review> getReviews(Long movieId, Long userId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Review> query = session.createQuery("FROM Review WHERE userId = :userId AND movieId = :movieId", Review.class);
            query.setParameter("userId", userId);
            query.setParameter("movieId", movieId);
            return query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Review> findReviewsByMovieId(Long movieId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Review> query = session.createQuery("FROM Review WHERE movieId = :movieId", Review.class);
            query.setParameter("movieId", movieId);
            return query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteReview(Long reviewId) throws DaoException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Review review = session.get(Review.class, reviewId);
            transaction = session.beginTransaction();
            session.remove(review);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
