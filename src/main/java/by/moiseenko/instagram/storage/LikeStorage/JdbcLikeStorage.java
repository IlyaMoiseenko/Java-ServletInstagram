package by.moiseenko.instagram.storage.LikeStorage;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Like;
import by.moiseenko.instagram.model.Post;
import by.moiseenko.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcLikeStorage implements LikeStorage {

    private static JdbcLikeStorage instance;

    private final String INSERT  = "insert into \"post_like\" (author_id, post_id) values (?, ?)";
    private final String FIND_ALL_BY_POST = "select count (*) from \"post_like\" where post_id = ?";
    private final String FIND_BY_USER = "select * from \"post_like\" where author_id = ?";

    private JdbcLikeStorage() { }

    public static JdbcLikeStorage getInstance() {
        if (instance == null)
            return new JdbcLikeStorage();

        return instance;
    }
    @Override
    public void save(Like like) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, like.getUser().getId());
            preparedStatement.setInt(2, like.getPost().getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public boolean findByUser(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
