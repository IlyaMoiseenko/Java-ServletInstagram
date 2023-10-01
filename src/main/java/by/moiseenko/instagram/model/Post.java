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

    public PostBuilder builder() {
        return new Post.PostBuilder();
    }

    public class PostBuilder {

        private int id;
        private User user;
        private String photo;
        private String description;
        private List<Comment> comments;
        private List<Like> likes;

        public PostBuilder id(int id) {
            this.id = id;

            return this;
        }

        public PostBuilder user(User user) {
            this.user = user;

            return this;
        }

        public PostBuilder photo(String photo) {
            this.photo = photo;

            return this;
        }

        public PostBuilder description(String description) {
            this.description = description;

            return this;
        }

        public PostBuilder comments(List<Comment> comments) {
            this.comments = comments;

            return this;
        }

        public PostBuilder likes(List<Like> likes) {
            this.likes = likes;

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

