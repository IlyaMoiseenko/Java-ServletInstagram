package by.moiseenko.instagram.storage.LikeStorage;

/*
    @author Ilya Moiseenko on 27.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcLikeStorage implements LikeStorage {

    private static JdbcLikeStorage instance;

    private final String INSERT  = "insert into \"post_like\" (author_id, post_id) values (?, ?)";

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
}
