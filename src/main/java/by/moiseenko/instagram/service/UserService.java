package by.moiseenko.instagram.service;

import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.dao.UserDao.JdbcUserDao;
import by.moiseenko.instagram.dao.UserDao.UserDao;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class UserService {

    private static UserService instance;

    private final UserDao<Integer> userDao = JdbcUserDao.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null)
            return new UserService();

        return instance;
    }

    public Integer add(User user) {
        return userDao.add(user);
    }

    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }
    public void update(User newUser, User currentUser) {
        userDao.update(newUser, currentUser);
    }
}
