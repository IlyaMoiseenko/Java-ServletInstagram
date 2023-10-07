package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 4.10.23
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hashtag {

    private int id;
    private String name;
    private Post post;

    private Hashtag() {}

    public static HashtagBuilder builder() {
        return new Hashtag().new HashtagBuilder();
    }

    public class HashtagBuilder {

        public HashtagBuilder id(int id) {
            Hashtag.this.id = id;

            return this;
        }

        public HashtagBuilder name(String name) {
            Hashtag.this.name = name;

            return this;
        }

        public HashtagBuilder post(Post post) {
            Hashtag.this.post = post;

            return this;
        }

        public Hashtag build() {
            return Hashtag.this;
        }
    }
}
