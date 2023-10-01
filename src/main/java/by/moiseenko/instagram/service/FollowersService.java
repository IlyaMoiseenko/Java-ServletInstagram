package by.moiseenko.instagram.service;

import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.storage.FollowerStorage.JdbcFollowerStorage;

/*
    @author Ilya Moiseenko on 1.10.23
*/
public class FollowersService {

    private static FollowersService instance;

    private final JdbcFollowerStorage followerStorage = JdbcFollowerStorage.getInstance();

    private FollowersService() {}

    public static FollowersService getInstance() {
        if (instance == null)
            return new FollowersService();

        return instance;
    }

    public void save(User parent, User child) {
        followerStorage.save(parent, child);
    }

    public boolean isFollow(User parent, User child) {
        return followerStorage.isFollow(parent, child);
    }

    public void unfollow(User parent, User child) {
        followerStorage.unfollow(parent, child);
    }
}
