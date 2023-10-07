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

import java.util.List;
import java.util.Optional;

public class PostService {

    private static PostService instance;

    private final PostDao postDao = JdbcPostDao.getInstance();
    private final CommentDao commentDao = JdbcCommentDao.getInstance();
    private final TagDao tagDao = JdbcTagDao.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null)
            return new PostService();

        return instance;
    }

    public int add(Post post) {
        return postDao.add(post);
    }

    public List<Post> findAllByUser(User user) {
        return postDao.findAllByUser(user);
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }

    public Optional<Post> findById(int id) {
        Optional<Post> postById = postDao.findById(id);

        if (postById.isPresent()) {
            Post post = postById.get();

            Optional<List<Comment>> allCommentsByPost = commentDao.getAllByPost(post);

            if (allCommentsByPost.isPresent()) {
                List<Comment> commentList = allCommentsByPost.get();

                post.setComments(commentList);
            }

            List<Hashtag> allHashtagsByPost = tagDao.findAllByPost(post);
            post.setHashtags(allHashtagsByPost);

            return Optional.of(post);
        }

        return Optional.empty();
    }

    public Optional<List<Post>> findAllByFollowing(User user) {
        return postDao.findAllByFollowing(user);
    }
}
