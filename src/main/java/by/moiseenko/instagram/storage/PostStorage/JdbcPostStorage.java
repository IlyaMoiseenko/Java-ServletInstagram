package by.moiseenko.instagram.storage.PostStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcPostStorage implements PostStorage {

    private static JdbcPostStorage instance;

    private final String INSERT = "insert into \"post\" (author_id, photo, description) values (?, ?, ?)";
    private final String SELECT_ALL_BY_USER = "select * from \"post\" where author_id = ?";
    private final String SELECT_BY_ID = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"post\".id = ?";
    private final String SELECT_ALL = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id";

    private JdbcPostStorage() {}

    public static JdbcPostStorage getInstance() {
        if (instance == null)
            return new JdbcPostStorage();

        return instance;
    }

    @Override
    public void add(Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> findAllByUser(User user) {
        List<Post> allPostsByUser = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .user(user)
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .build();

                allPostsByUser.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPostsByUser;
    }

    @Override
    public List<Post> findAll() {
        List<Post> allPost = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .surname(resultSet.getString(7))
                        .username(resultSet.getString(8))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .email(resultSet.getString(10))
                        .password(resultSet.getString(11))
                        .build();

                post.setUser(user);

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(13))
                        .name(resultSet.getString(14))
                        .build();

                user.setCountry(country);

                allPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPost;
    }

    @Override
    public Optional<Post> findById(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .surname(resultSet.getString(7))
                        .username(resultSet.getString(8))
                        .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .email(resultSet.getString(10))
                        .password(resultSet.getString(11))
                        .build();

                post.setUser(user);

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(13))
                        .name(resultSet.getString(14))
                        .build();

                user.setCountry(country);

                return Optional.of(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
