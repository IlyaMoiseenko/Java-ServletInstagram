package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;
import by.moiseenko.instagram.storage.CommentStorage.CommentStorage;
import by.moiseenko.instagram.storage.CommentStorage.JdbcCommentStorage;
import by.moiseenko.instagram.storage.PostStorage.JdbcPostStorage;
import by.moiseenko.instagram.storage.PostStorage.PostStorage;
import by.moiseenko.instagram.storage.TagStorage.JdbcTagStorage;
import by.moiseenko.instagram.storage.TagStorage.TagStorage;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PostService {

    private static PostService instance;

    private final PostStorage postStorage = JdbcPostStorage.getInstance();
    private final CommentStorage commentStorage = JdbcCommentStorage.getInstance();
    private final TagStorage tagStorage = JdbcTagStorage.getInstance();

    private PostService() {}

    public static PostService getInstance() {
        if (instance == null)
            return new PostService();

        return instance;
    }

    public int add(Post post) {
        return postStorage.add(post);
    }

    public List<Post> findAllByUser(User user) {
        return postStorage.findAllByUser(user);
    }

    public List<Post> findAll() {
        return postStorage.findAll();
    }

    public Optional<Post> findById(int id) {
        Optional<Post> postById = postStorage.findById(id);

        if (postById.isPresent()) {
            Post post = postById.get();

            Optional<List<Comment>> allCommentsByPost = commentStorage.getAllByPost(post);

            if (allCommentsByPost.isPresent()) {
                List<Comment> commentList = allCommentsByPost.get();

                post.setComments(commentList);
            }

            List<Hashtag> allHashtagsByPost = tagStorage.findAllByPost(post);
            post.setHashtags(allHashtagsByPost);

            return Optional.of(post);
        }

        return Optional.empty();
    }

    public Optional<List<Post>> findAllByFollowing(User user) {
        return postStorage.findAllByFollowing(user);
    }
}
