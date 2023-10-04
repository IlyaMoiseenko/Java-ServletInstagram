package by.moiseenko.instagram.model;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import java.util.List;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
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

