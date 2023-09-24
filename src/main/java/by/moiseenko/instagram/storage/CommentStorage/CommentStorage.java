package by.moiseenko.instagram.storage.CommentStorage;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.model.Comment;

public interface CommentStorage {

    void save(Comment comment);
}
