package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

    private int id;
    private User user;
    private Post post;

    private Like() {}

    public static LikeBuilder builder() {
        return new Like().new LikeBuilder();
    }

    public class LikeBuilder {

        private LikeBuilder() {}

        public LikeBuilder id(int id) {
            Like.this.id = id;

            return this;
        }

        public LikeBuilder user(User user) {
            Like.this.user = user;

            return this;
        }

        public LikeBuilder post(Post post) {
            Like.this.post = post;

            return this;
        }

        public Like build() {
            return Like.this;
        }
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

