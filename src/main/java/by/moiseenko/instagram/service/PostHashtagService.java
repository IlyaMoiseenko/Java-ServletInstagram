package by.moiseenko.instagram.service;

import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.dao.PostHashtagDao.JdbcPostHashTagDao;
import by.moiseenko.instagram.dao.PostHashtagDao.PostHashTagDao;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public class PostHashtagService {

    private static PostHashtagService instance;

    private final PostHashTagDao postHashTagDao = JdbcPostHashTagDao.getInstance();

    private PostHashtagService() {}

    public static PostHashtagService getInstance() {
        if (instance == null)
            instance = new PostHashtagService();

        return instance;
    }

    public void save(Hashtag hashtag, Post post) {
        postHashTagDao.add(hashtag, post);
    }
}
