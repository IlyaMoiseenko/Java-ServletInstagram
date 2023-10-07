package by.moiseenko.instagram.dao.LikeDao;

import by.moiseenko.instagram.entity.Like;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

/*
    @author Ilya Moiseenko on 27.09.23
*/
public interface LikeDao<ID> {

    ID save(Like like);

    int findAllByPost(Post post);

    boolean findByUserAndPost(User user, Post post);
    void removeByUserAndPost(User user, Post post);
}
