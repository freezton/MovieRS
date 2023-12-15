package by.bsuir.movieratingsystem.dao.impl;

import by.bsuir.movieratingsystem.dao.MovieDao;
import by.bsuir.movieratingsystem.entity.Movie;
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
public class MovieDaoImpl implements MovieDao {

    private final SessionFactory sessionFactory;
    
    @Override
    public Optional<Movie> findById(long id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie WHERE id = :id", Movie.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Movie item) throws DaoException {
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
    public List<Movie> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie", Movie.class);
            return query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateMovie(Movie item) throws DaoException {
        Transaction transaction = null;
        Session session = null;
        try {
            if (item.getRating() < 0)
                item.setRating(findById(item.getId()).orElseThrow(() -> new HibernateException("no such film")).getRating());
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(item);
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
