package by.moiseenko.instagram.model;

/*
    @author Ilya Moiseenko on 4.10.23
*/

public class Hashtag {

    private int id;
    private String name;
    private Post post;

    private Hashtag() {}

    public static HashtagBuilder builder() {
        return new Hashtag().new HashtagBuilder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
