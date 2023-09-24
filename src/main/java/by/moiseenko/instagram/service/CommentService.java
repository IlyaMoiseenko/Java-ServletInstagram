package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.storage.CommentStorage.CommentStorage;
import by.moiseenko.instagram.storage.CommentStorage.JdbcCommentStorage;

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
}
