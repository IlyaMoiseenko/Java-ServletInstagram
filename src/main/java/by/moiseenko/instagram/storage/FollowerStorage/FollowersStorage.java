package by.moiseenko.instagram.storage.FollowerStorage;

import by.moiseenko.instagram.model.User;

/*
    @author Ilya Moiseenko on 1.10.23
*/
public interface FollowersStorage {

    void save(User parent, User child);
    boolean isFollow(User parent, User child);
    void unfollow(User parent, User child);
    int getFollowersByUser(User user);
    int getFollowingByUser(User user);
}
