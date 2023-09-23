package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.storage.PostStorage.JdbcPostStorage;
import by.moiseenko.instagram.storage.PostStorage.PostStorage;

import java.sql.Statement;
import java.util.List;

public class PostService {

    private static PostService instance;

    private final PostStorage postStorage = JdbcPostStorage.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null)
            return new PostService();

        return instance;
    }

    public void add(Post post) {
        postStorage.add(post);
    }

    public List<Post> findAllByUser(User user) {
        return postStorage.findAllByUser(user);
    }
}
