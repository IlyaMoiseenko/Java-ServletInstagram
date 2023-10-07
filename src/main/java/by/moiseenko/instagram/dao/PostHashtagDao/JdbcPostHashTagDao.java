package by.moiseenko.instagram.dao.PostHashtagDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    @author Ilya Moiseenko on 4.10.23
*/
public class JdbcPostHashTagDao implements PostHashTagDao {

    private static JdbcPostHashTagDao instance;

    private final String INSERT = "insert into \"post_hashtag\" (hashtag_id, post_id) values (?, ?)";

    private JdbcPostHashTagDao() {}

    public static JdbcPostHashTagDao getInstance() {
        if (instance == null)
            instance = new JdbcPostHashTagDao();

        return instance;
    }

    @Override
    public void add(Hashtag hashtag, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, hashtag.getId());
            preparedStatement.setInt(2, post.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
