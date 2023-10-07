package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.entity.Comment;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.dao.CommentDao.CommentDao;
import by.moiseenko.instagram.dao.CommentDao.JdbcCommentDao;

import java.util.List;
import java.util.Optional;

public class CommentService {

    private static CommentService instance;

    private final CommentDao<Integer> commentDao = JdbcCommentDao.getInstance();

    private CommentService() {}

    public static CommentService getInstance() {
        if (instance == null)
            return new CommentService();

        return instance;
    }

    public void add(Comment comment) {
        commentDao.save(comment);
    }

    public Iterable<Comment> findAllByPost(Post post) {
        return commentDao.getAllByPost(post);
    }
}
