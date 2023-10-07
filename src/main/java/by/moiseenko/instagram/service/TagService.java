package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 4.10.23
*/

import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.dao.PostHashtagDao.JdbcPostHashTagDao;
import by.moiseenko.instagram.dao.PostHashtagDao.PostHashTagDao;
import by.moiseenko.instagram.dao.TagDao.JdbcTagDao;
import by.moiseenko.instagram.dao.TagDao.TagDao;

import java.util.List;
import java.util.Optional;

public class TagService {

    private static TagService instance;

    private final TagDao tagDao = JdbcTagDao.getInstance();
    private final PostHashTagDao postHashTagDao = JdbcPostHashTagDao.getInstance();

    private TagService() {}

    public static TagService getInstance() {
        if (instance == null)
            instance = new TagService();

        return instance;
    }

    public void save(List<Hashtag> hashtags) {
        for (Hashtag hashtag : hashtags) {
            Optional<Hashtag> hashtagByName = this.findByName(hashtag.getName());
            if (!hashtagByName.isPresent())
                tagDao.save(hashtag);

            Optional<Hashtag> hashtagForPost = this.findByName(hashtag.getName());
            if (hashtagForPost.isPresent()) {
                Hashtag curr = hashtagForPost.get();

                postHashTagDao.add(curr, hashtag.getPost());
            }
        }
    }

    public Optional<Hashtag> findByName(String name) {
        return tagDao.findByName(name);
    }

    public List<Hashtag> findAllByPost(Post post) {
        return tagDao.findAllByPost(post);
    }
}
