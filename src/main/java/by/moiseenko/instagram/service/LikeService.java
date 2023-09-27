package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.model.Like;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.storage.LikeStorage.JdbcLikeStorage;
import by.moiseenko.instagram.storage.LikeStorage.LikeStorage;

public class LikeService {

    private static LikeService instance;

    private final LikeStorage likeStorage = JdbcLikeStorage.getInstance();

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null)
            return new LikeService();

        return instance;
    }

    public void save(Like like) {
        likeStorage.save(like);
    }

    public int findAllByPost(Post post) {
        return likeStorage.findAllByPost(post);
    }

    public boolean findByUserAndPost(User user, Post post) {
        return likeStorage.findByUserAndPost(user, post);
    }

    public void removeByUserAndPost(User user, Post post) {
        likeStorage.removeByUserAndPost(user, post);
    }
}
