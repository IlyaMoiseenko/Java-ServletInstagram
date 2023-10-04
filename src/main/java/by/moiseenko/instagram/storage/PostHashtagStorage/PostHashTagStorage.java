package by.moiseenko.instagram.storage.PostHashtagStorage;

import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public interface PostHashTagStorage {

    void add(Hashtag hashtag, Post post);
}
