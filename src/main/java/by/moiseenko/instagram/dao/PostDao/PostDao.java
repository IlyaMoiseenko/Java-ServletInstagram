package by.moiseenko.instagram.dao.PostDao;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

import java.util.Optional;

public interface PostDao<ID> {
    ID add(Post post);
    Iterable<Post> findAllByUser(User user);
    Iterable<Post> findAll();
    Optional<Post> findById(ID id);
    Iterable<Post> findAllByFollowing(User user);
}
