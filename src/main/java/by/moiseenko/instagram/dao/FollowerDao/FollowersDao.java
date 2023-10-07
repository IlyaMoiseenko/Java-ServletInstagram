package by.moiseenko.instagram.dao.FollowerDao;

import by.moiseenko.instagram.entity.User;

/*
    @author Ilya Moiseenko on 1.10.23
*/
public interface FollowersDao {

    void save(User parent, User child);
    boolean isFollow(User parent, User child);
    void unfollow(User parent, User child);
    int getFollowersByUser(User user);
    int getFollowingByUser(User user);
}
