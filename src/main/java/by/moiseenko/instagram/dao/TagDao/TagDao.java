package by.moiseenko.instagram.dao.TagDao;

import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 4.10.23
*/

public interface TagDao<ID> {

    ID save(Hashtag hashtag);
    Optional<Hashtag> findByName(String name);
    Iterable<Hashtag> findAllByPost(Post post);
}
