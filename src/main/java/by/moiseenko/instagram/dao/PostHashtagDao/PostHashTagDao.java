package by.moiseenko.instagram.dao.PostHashtagDao;

import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public interface PostHashTagDao {

    void add(Hashtag hashtag, Post post);
}
