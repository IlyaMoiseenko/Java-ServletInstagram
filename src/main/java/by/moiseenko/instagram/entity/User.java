package by.moiseenko.instagram.entity;


/*
    @author Ilya Moiseenko on 19.09.23
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String photo;
    private String email;
    private String password;
    private Country country;
    private City city;

    private Iterable<Post> posts;
    private Iterable<Comment> comments;
    private Iterable<Like> likes;

    private User() {}

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {

        private UserBuilder() {}

        public UserBuilder id(int id) {
            User.this.id = id;

            return this;
        }

        public UserBuilder name(String name) {
            User.this.name = name;

            return this;
        }

        public UserBuilder surname(String surname) {
            User.this.surname = surname;

            return this;
        }

        public UserBuilder username(String username) {
            User.this.username = username;

            return this;
        }

        public UserBuilder photo(String photo) {
            User.this.photo = photo;

            return this;
        }

        public UserBuilder email(String email) {
            User.this.email = email;

            return this;
        }

        public UserBuilder password(String password) {
            User.this.password = password;

            return this;
        }

        public UserBuilder country(Country country) {
            User.this.country = country;

            return this;
        }

        public UserBuilder city(City city) {
            User.this.city = city;

            return this;
        }

        public User build() {
            return User.this;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", countryId=" + country +
                '}';
    }
}

