package by.moiseenko.instagram.storage.FollowerStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    @author Ilya Moiseenko on 1.10.23
*/
public class JdbcFollowerStorage implements FollowersStorage {

    private static JdbcFollowerStorage instance;

    private final String INSERT = "insert into \"followers\" (parent_id, child_id) values (?, ?)";
    private final String SELECT_BY_PARENT_AND_CHILD = "select * from \"followers\" where parent_id = ? and child_id = ?";
    private final String DELETE_BY_PARENT_AND_CHILD = "delete from \"followers\" where parent_id = ? and child_id = ?";

    private JdbcFollowerStorage() {}

    public static JdbcFollowerStorage getInstance() {
        if (instance == null)
            instance = new JdbcFollowerStorage();

        return instance;
    }
    @Override
    public void save(User parent, User child) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, parent.getId());
            preparedStatement.setInt(2, child.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFollow(User parent, User child) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PARENT_AND_CHILD);
            preparedStatement.setInt(1, parent.getId());
            preparedStatement.setInt(2, child.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void unfollow(User parent, User child) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_PARENT_AND_CHILD);
            preparedStatement.setInt(1, parent.getId());
            preparedStatement.setInt(2, child.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
