package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 4.10.23
*/

import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.storage.PostHashtagStorage.JdbcPostHashTagStorage;
import by.moiseenko.instagram.storage.PostHashtagStorage.PostHashTagStorage;
import by.moiseenko.instagram.storage.TagStorage.JdbcTagStorage;
import by.moiseenko.instagram.storage.TagStorage.TagStorage;

import java.util.List;
import java.util.Optional;

public class TagService {

    private static TagService instance;

    private final TagStorage tagStorage = JdbcTagStorage.getInstance();
    private final PostHashTagStorage postHashTagStorage = JdbcPostHashTagStorage.getInstance();

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
                tagStorage.save(hashtag);

            Optional<Hashtag> hashtagForPost = this.findByName(hashtag.getName());
            if (hashtagForPost.isPresent()) {
                Hashtag curr = hashtagForPost.get();

                postHashTagStorage.add(curr, hashtag.getPost());
            }
        }
    }

    public Optional<Hashtag> findByName(String name) {
        return tagStorage.findByName(name);
    }

    public List<Hashtag> findAllByPost(Post post) {
        return tagStorage.findAllByPost(post);
    }
}
