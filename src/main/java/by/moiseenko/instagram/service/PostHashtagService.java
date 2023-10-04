package by.moiseenko.instagram.service;

import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.storage.PostHashtagStorage.JdbcPostHashTagStorage;
import by.moiseenko.instagram.storage.PostHashtagStorage.PostHashTagStorage;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public class PostHashtagService {

    private static PostHashtagService instance;

    private final PostHashTagStorage postHashTagStorage = JdbcPostHashTagStorage.getInstance();

    private PostHashtagService() {}

    public static PostHashtagService getInstance() {
        if (instance == null)
            instance = new PostHashtagService();

        return instance;
    }

    public void save(Hashtag hashtag, Post post) {
        postHashTagStorage.add(hashtag, post);
    }
}
