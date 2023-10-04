package by.moiseenko.instagram.storage.PostStorage;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.util.List;
import java.util.Optional;

public interface PostStorage {
    int add(Post post);
    List<Post> findAllByUser(User user);
    List<Post> findAll();
    Optional<Post> findById(int id);
    Optional<List<Post>> findAllByFollowing(User user);
}
