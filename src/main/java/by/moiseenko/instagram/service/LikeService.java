package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.model.Like;
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
}
