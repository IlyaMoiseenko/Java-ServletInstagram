package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Post {

    private int id;
    private User user;
    private String photo;
    private String description;
    private List<Comment> comments;
    private List<Like> likes;
    private List<Hashtag> hashtags;

    private Post() {}

    public static PostBuilder builder() {
        return new Post().new PostBuilder();
    }

    public class PostBuilder {

        private PostBuilder() {}

        public PostBuilder id(int id) {
            Post.this.id = id;

            return this;
        }

        public PostBuilder user(User user) {
            Post.this.user = user;

            return this;
        }

        public PostBuilder photo(String photo) {
            Post.this.photo = photo;

            return this;
        }

        public PostBuilder description(String description) {
            Post.this.description = description;

            return this;
        }

        public PostBuilder comments(List<Comment> comments) {
            Post.this.comments = comments;

            return this;
        }

        public PostBuilder likes(List<Like> likes) {
            Post.this.likes = likes;

            return this;
        }

        public PostBuilder hashtags(List<Hashtag> hashtags) {
            Post.this.hashtags = hashtags;

            return this;
        }

        public Post build() {
            return Post.this;
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

