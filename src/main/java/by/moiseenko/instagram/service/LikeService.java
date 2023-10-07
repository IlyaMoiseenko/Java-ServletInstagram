package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.entity.Like;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.dao.LikeDao.JdbcLikeDao;
import by.moiseenko.instagram.dao.LikeDao.LikeDao;

public class LikeService {

    private static LikeService instance;

    private final LikeDao<Integer> likeDao = JdbcLikeDao.getInstance();

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null)
            return new LikeService();

        return instance;
    }

    public Integer save(Like like) {
        return likeDao.save(like);
    }

    public int findAllByPost(Post post) {
        return likeDao.findAllByPost(post);
    }

    public boolean findByUserAndPost(User user, Post post) {
        return likeDao.findByUserAndPost(user, post);
    }

    public void removeByUserAndPost(User user, Post post) {
        likeDao.removeByUserAndPost(user, post);
    }
}
