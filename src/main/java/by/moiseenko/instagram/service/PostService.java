package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Comment;
import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;
import by.moiseenko.instagram.dao.CommentDao.CommentDao;
import by.moiseenko.instagram.dao.CommentDao.JdbcCommentDao;
import by.moiseenko.instagram.dao.PostDao.JdbcPostDao;
import by.moiseenko.instagram.dao.PostDao.PostDao;
import by.moiseenko.instagram.dao.TagDao.JdbcTagDao;
import by.moiseenko.instagram.dao.TagDao.TagDao;

import java.util.Optional;

public class PostService {

    private static PostService instance;

    private final PostDao<Integer> postDao = JdbcPostDao.getInstance();
    private final CommentDao<Integer> commentDao = JdbcCommentDao.getInstance();
    private final TagDao<Integer> tagDao = JdbcTagDao.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null)
            return new PostService();

        return instance;
    }

    public Optional<Integer> add(Post post) {
        return postDao.add(post);
    }

    public Iterable<Post> findAllByUser(User user) {
        return postDao.findAllByUser(user);
    }

    public Iterable<Post> findAll() {
        return postDao.findAll();
    }

    public Optional<Post> findById(Integer id) {
        Optional<Post> postById = postDao.findById(id);

        if (postById.isPresent()) {
            Post post = postById.get();

            Iterable<Comment> allCommentsByPost = commentDao.getAllByPost(post);

            post.setComments(allCommentsByPost);

            Iterable<Hashtag> allHashtagsByPost = tagDao.findAllByPost(post);
            post.setHashtags(allHashtagsByPost);

            return Optional.of(post);
        }

        return Optional.empty();
    }

    public Iterable<Post> findAllByFollowing(User user) {
        return postDao.findAllByFollowing(user);
    }
}
