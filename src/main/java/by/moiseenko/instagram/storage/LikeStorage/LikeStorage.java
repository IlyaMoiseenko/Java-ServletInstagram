package by.moiseenko.instagram.storage.LikeStorage;

import by.moiseenko.instagram.model.Like;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 27.09.23
*/
public interface LikeStorage {

    void save(Like like);

    int findAllByPost(Post post);

    boolean findByUser(User user);
}
