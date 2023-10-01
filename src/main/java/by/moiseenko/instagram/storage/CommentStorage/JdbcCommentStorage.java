package by.moiseenko.instagram.storage.CommentStorage;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Comment;
import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class JdbcCommentStorage implements CommentStorage {

    private static JdbcCommentStorage instance;

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_ALL_BY_POST = "select * from \"comment\" join \"human\" on \"comment\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id where \"comment\".post_id = ?";

    private JdbcCommentStorage() {}

    public static JdbcCommentStorage getInstance() {
        if (instance == null)
            return new JdbcCommentStorage();

        return instance;
    }
    @Override
    public void save(Comment comment) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<Comment>> getAllByPost(Post post) {
        List<Comment> commentList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = Comment
                        .builder()
                        .id(resultSet.getInt(1))
                        .post(post).text(resultSet.getString(3))
                        .build();

                User user = User
                        .builder()
                        .id(resultSet.getInt(4))
                        .name(resultSet.getString(5))
                        .surname(resultSet.getString(6))
                        .username(resultSet.getString(7))
                        .photo(resultSet.getString(8))
                        .email(resultSet.getString(9))
                        .password(resultSet.getString(10))
                        .build();

                Country country = Country
                        .builder()
                        .id(resultSet.getInt(12))
                        .name(resultSet.getString(13))
                        .build();

                user.setCountry(country);
                comment.setUser(user);

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(commentList);
    }
}
