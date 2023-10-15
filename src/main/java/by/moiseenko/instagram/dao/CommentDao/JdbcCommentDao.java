package by.moiseenko.instagram.dao.CommentDao;

/*
    @author Ilya Moiseenko on 24.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Comment;
import by.moiseenko.instagram.entity.Country;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class JdbcCommentDao implements CommentDao<Integer> {

    // Fields
    private static JdbcCommentDao instance;

    private final String INSERT = "insert into \"comment\" (author_id, post_id, text) values (?, ?, ?)";
    private final String GET_ALL_BY_POST = "select * from \"comment\" join \"human\" on \"comment\".author_id = \"human\".id join \"country\" on \"human\".country_id = \"country\".id join \"post\" on \"comment\".post_id = \"post\".id where \"comment\".post_id = ?";
    private final String REMOVE_BY_ID = "delete from \"comment\" where id = ?";

    // Constructors
    private JdbcCommentDao() {}

    // Methods
    public static JdbcCommentDao getInstance() {
        if (instance == null)
            return new JdbcCommentDao();

        return instance;
    }

    @Override
    public Integer save(Comment comment) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Comment> getAllByPost(Post post) {
        List<Comment> commentList = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = buildCommentEntityFromResultSet(resultSet);

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    private Comment buildCommentEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = Comment
                .builder()
                .id(resultSet.getInt(1))
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

        Country country = Country
                .builder()
                .id(resultSet.getInt(13))
                .name(resultSet.getString(14))
                .build();

        Post post = Post
                .builder()
                .id(16)
                .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(18)))
                .description(resultSet.getString(19))
                .build();

        user.setCountry(country);
        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }

    @Override
    public void removeById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
