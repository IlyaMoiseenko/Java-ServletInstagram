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
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setPost(post);
                comment.setText(resultSet.getString(3));

                User user = new User(
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        Base64.getEncoder().encodeToString(resultSet.getBytes(8)),
                        resultSet.getString(9),
                        resultSet.getString(10)
                );

                Country country = new Country();
                country.setId(resultSet.getInt(12));
                country.setName(resultSet.getString(13));

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
