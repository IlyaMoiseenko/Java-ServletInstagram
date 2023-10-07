package by.moiseenko.instagram.dao.TagDao;

import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public interface TagDao {

    void save(Hashtag hashtag);
    Optional<Hashtag> findByName(String name);
    List<Hashtag> findAllByPost(Post post);
}
