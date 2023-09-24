package by.moiseenko.instagram.storage.CommentStorage;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.model.Post;

import java.util.List;
import java.util.Optional;

public interface CommentStorage {

    void save(Comment comment);
    Optional<List<Comment>> getAllByPost(Post post);
}
