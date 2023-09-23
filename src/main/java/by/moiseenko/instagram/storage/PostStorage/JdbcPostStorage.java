package by.moiseenko.instagram.storage.PostStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcPostStorage implements PostStorage {

    private static JdbcPostStorage instance;

    private final String INSERT = "insert into \"post\" (author_id, photo, description) values (?, ?, ?)";
    private final String SELECT_ALL_BY_USER = "select * from \"post\" where author_id = ?";
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
                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setUser(user);
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)));
                post.setDescription(resultSet.getString(4));

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
                Post post = new Post();
                post.setId(resultSet.getInt(1));
                post.setPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)));
                post.setDescription(resultSet.getString(4));

                User user = new User(
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        Base64.getEncoder().encodeToString(resultSet.getBytes(9)),
                        resultSet.getString(10),
                        resultSet.getString(11)
                );

                post.setUser(user);

                Country country = new Country();
                country.setId(resultSet.getInt(13));
                country.setName(resultSet.getString(14));

                user.setCountry(country);

                allPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPost;
    }
}
