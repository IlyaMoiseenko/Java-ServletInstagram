package by.moiseenko.instagram.dao.CommentDao;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.entity.Comment;
import by.moiseenko.instagram.entity.Post;

public interface CommentDao<ID> {

    ID save(Comment comment);
    Iterable<Comment> getAllByPost(Post post);
    void removeById(ID id);
}
