package by.moiseenko.instagram.storage.UserStorage;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.User;

import java.util.Optional;

public interface UserStorage {

    void add(User user);
    Optional<User> getByUsername(String username);
}
