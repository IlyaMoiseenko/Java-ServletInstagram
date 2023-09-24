package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.storage.CommentStorage.CommentStorage;
import by.moiseenko.instagram.storage.CommentStorage.JdbcCommentStorage;

import java.util.List;
import java.util.Optional;

public class CommentService {

    private static CommentService instance;

    private final CommentStorage commentStorage = JdbcCommentStorage.getInstance();

    private CommentService() {}

    public static CommentService getInstance() {
        if (instance == null)
            return new CommentService();

        return instance;
    }

    public void add(Comment comment) {
        commentStorage.save(comment);
    }

    public Optional<List<Comment>> findAllByPost(Post post) {
        return commentStorage.getAllByPost(post);
    }
}
