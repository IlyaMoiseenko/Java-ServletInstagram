package by.moiseenko.instagram.storage.TagStorage;

import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public interface TagStorage {

    void save(Hashtag hashtag);
    Optional<Hashtag> findByName(String name);
    List<Hashtag> findAllByPost(Post post);
}
