package by.moiseenko.instagram.service;

import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.storage.UserStorage.JdbcUserStorage;
import by.moiseenko.instagram.storage.UserStorage.UserStorage;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class UserService {

    private static UserService instance;

    private final UserStorage userStorage = JdbcUserStorage.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null)
            return new UserService();

        return instance;
    }

    public void add(User user) {
        userStorage.add(user);
    }

    public Optional<User> getByUsername(String username) {
        return userStorage.getByUsername(username);
    }
}
