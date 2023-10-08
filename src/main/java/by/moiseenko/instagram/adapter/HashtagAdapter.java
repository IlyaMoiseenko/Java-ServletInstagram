package by.moiseenko.instagram.adapter;

/*
    @author Ilya Moiseenko on 8.10.23
*/

import by.moiseenko.instagram.entity.Hashtag;

import java.util.ArrayList;
import java.util.List;

public class HashtagAdapter {

    public List<Hashtag> converToList(String hashtagsString) {
        List<Hashtag> hashtagList = new ArrayList<>();

        hashtagsString = hashtagsString.replaceAll("#", "");
        String[] hashtags = hashtagsString.split(" ");

        for (String hashtag : hashtags) {
            Hashtag newHashtag = Hashtag
                    .builder()
                    .name(hashtag)
                    .build();

            hashtagList.add(newHashtag);
        }

        return hashtagList;
    }
}
