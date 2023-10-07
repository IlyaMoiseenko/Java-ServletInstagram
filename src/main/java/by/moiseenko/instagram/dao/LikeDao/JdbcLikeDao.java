package by.moiseenko.instagram.dao.LikeDao;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Like;
import by.moiseenko.instagram.entity.Post;
import by.moiseenko.instagram.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcLikeDao implements LikeDao<Integer> {

    // Fields
    private static JdbcLikeDao instance;

    private final String INSERT  = "insert into \"post_like\" (author_id, post_id) values (?, ?)";
    private final String FIND_ALL_BY_POST = "select count (*) from \"post_like\" where post_id = ?";
    private final String FIND_BY_USER_AND_POST = "select * from \"post_like\" where author_id = ? and post_id = ?";
    private final String REMOVE_BY_USER_AND_POST = "delete from \"post_like\" where author_id = ? and post_id = ?";

    // Constructors
    private JdbcLikeDao() { }

    // Methods
    public static JdbcLikeDao getInstance() {
        if (instance == null)
            return new JdbcLikeDao();

        return instance;
    }

    @Override
    public Integer save(Like like) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getPost().getId());

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
    public int findAllByPost(Post post) {
        int countOfLike = 0;

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                countOfLike = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countOfLike;
    }

    @Override
    public boolean findByUserAndPost(User user, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_AND_POST);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void removeByUserAndPost(User user, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_USER_AND_POST);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, post.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
