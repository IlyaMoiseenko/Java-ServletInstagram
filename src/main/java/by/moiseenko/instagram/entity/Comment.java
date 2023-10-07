package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class Comment {

    private int id;
    private User user;
    private Post post;
    private String text;

    private Comment() {}

    public static CommentBuilder builder() {
        return new Comment().new CommentBuilder();
    }

    public class CommentBuilder {

        private CommentBuilder() {}

        public CommentBuilder id(int id) {
            Comment.this.id = id;

            return this;
        }

        public CommentBuilder user(User user) {
            Comment.this.user = user;

            return this;
        }

        public CommentBuilder post(Post post) {
            Comment.this.post = post;

            return this;
        }

        public CommentBuilder text(String text) {
            Comment.this.text = text;

            return this;
        }

        public Comment build() {
            return Comment.this;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", text='" + text + '\'' +
                '}';
    }
}
