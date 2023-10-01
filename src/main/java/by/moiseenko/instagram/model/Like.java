package by.moiseenko.instagram.model;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class Like {

    private int id;
    private User user;
    private Post post;

    public LikeBuilder builder() {
        return new Like.LikeBuilder();
    }

    public class LikeBuilder {

        private int id;
        private User user;
        private Post post;

        public LikeBuilder id(int id) {
            this.id = id;

            return this;
        }

        public LikeBuilder user(User user) {
            this.user = user;

            return this;
        }

        public LikeBuilder post(Post post) {
            this.post = post;

            return this;
        }

        public Like build() {
            return Like.this;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}

