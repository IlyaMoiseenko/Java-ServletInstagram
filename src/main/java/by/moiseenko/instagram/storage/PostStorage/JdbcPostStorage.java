package by.moiseenko.instagram.storage.PostStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcPostStorage implements PostStorage {

    private static JdbcPostStorage instance;

    private final String INSERT = "insert into \"post\" (author_id, photo, description) values (?, ?, ?)";

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
}
