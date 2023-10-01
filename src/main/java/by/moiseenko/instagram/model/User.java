package by.moiseenko.instagram.model;


/*
    @author Ilya Moiseenko on 19.09.23
*/

import java.util.List;

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

    private List<Post> posts;
    private List<Comment> comments;
    private List<Like> likes;

    public UserBuilder builder() {
        return new User.UserBuilder();
    }

    public class UserBuilder {
        private int id;
        private String name;
        private String surname;
        private String username;
        private String photo;
        private String email;
        private String password;
        private Country country;
        private City city;

        public UserBuilder id(int id) {
            this.id = id;

            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;

            return this;
        }

        public UserBuilder surname(String surname) {
            this.surname = surname;

            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;

            return this;
        }

        public UserBuilder photo(String photo) {
            this.photo = photo;

            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;

            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;

            return this;
        }

        public UserBuilder country(Country country) {
            this.country = country;

            return this;
        }

        public UserBuilder city(City city) {
            this.city = city;

            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public User(int id, String name, String surname, String username, String photo, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.photo = photo;
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String username, String photo, String email, String password, Country country, City city) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
    }

    public User(String name, String email, String password, String photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

