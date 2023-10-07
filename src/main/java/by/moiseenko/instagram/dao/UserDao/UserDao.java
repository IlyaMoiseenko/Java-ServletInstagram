package by.moiseenko.instagram.dao.UserDao;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.User;

import java.util.Optional;

public interface UserDao {

    void add(User user);
    Optional<User> getByUsername(String username);
    void update(User newUser, User currentUser);
}
