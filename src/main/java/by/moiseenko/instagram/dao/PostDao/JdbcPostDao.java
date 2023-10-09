package by.moiseenko.instagram.dao.PostDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Country;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class JdbcPostDao implements PostDao<Integer> {

    // Fields
    private static JdbcPostDao instance;

    private final String INSERT = "insert into \"post\" (author_id, photo, description) values (?, ?, ?)";
    private final String SELECT_ALL_BY_USER = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"post\".author_id = ?";
    private final String SELECT_BY_ID = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"post\".id = ?";
    private final String SELECT_ALL = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id";
    private final String SELECT_ALL_BY_FOLLOWING = "select * from \"post\" join \"human\" on \"post\".author_id = \"human\".id join \"followers\" on \"human\".id = \"followers\".child_id join \"country\" on \"human\".country_id = \"country\".id where \"followers\".parent_id = ?";

    // Constructors
    private JdbcPostDao() {}

    // Methods
    public static JdbcPostDao getInstance() {
        if (instance == null)
            return new JdbcPostDao();

        return instance;
    }

    @Override
    public Optional<Integer> add(Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, post.getUser().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getPhoto()));
            preparedStatement.setString(3, post.getDescription());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    return Optional.of(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> findAllByUser(User user) {
        List<Post> allPostsByUser = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = buildPostEntityFromResultSet(resultSet);

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
                Post post = buildPostEntityFromResultSet(resultSet);

                allPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPost;
    }

    @Override
    public Optional<Post> findById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Post post = buildPostEntityFromResultSet(resultSet);

                return Optional.of(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> findAllByFollowing(User user) {
        List<Post> allPost = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_FOLLOWING);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = buildPostEntityFromResultSet(resultSet);

                allPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPost;
    }

    private Post buildPostEntityFromResultSet(ResultSet resultSet) throws SQLException {
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

        return post;
    }
}
