package by.moiseenko.instagram.dao.PostDao;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    int add(Post post);
    List<Post> findAllByUser(User user);
    List<Post> findAll();
    Optional<Post> findById(int id);
    Optional<List<Post>> findAllByFollowing(User user);
}
