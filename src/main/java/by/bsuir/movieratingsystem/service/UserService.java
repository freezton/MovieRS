package by.bsuir.movieratingsystem.service;

import by.bsuir.movieratingsystem.dao.UserDao;
import by.bsuir.movieratingsystem.entity.Role;
import by.bsuir.movieratingsystem.entity.User;
import by.bsuir.movieratingsystem.exception.DaoException;
import by.bsuir.movieratingsystem.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public void register(String username, String password) throws ServiceException {
        User user = new User(null, username, password, Role.USER, 50, false);
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Such user already exists");
        }
    }

    public User login(String username, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException("Invalid user credentials");
        }
        if (user.isEmpty() || !BCrypt.checkpw(password, user.get().getPassword())) {
            throw new ServiceException("Invalid user credentials");
        }
        return user.get();
    }


    @SneakyThrows
    public List<User> getUserList() {
        return userDao.findAll();
    }


    @SneakyThrows
    public void updateUser(Long id, int status, boolean isBanned) {
        User user = userDao.findById(id).get();
        User newUser = new User(id, user.getUsername(), user.getPassword(), user.getRole(), status, isBanned);
        userDao.updateUser(newUser);
    }
}
