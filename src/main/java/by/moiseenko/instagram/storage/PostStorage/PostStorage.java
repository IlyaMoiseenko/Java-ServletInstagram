package by.moiseenko.instagram.storage.PostStorage;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.util.List;

public interface PostStorage {
    void add(Post post);
    List<Post> findAllByUser(User user);
}
