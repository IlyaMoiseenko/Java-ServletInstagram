package by.moiseenko.instagram.dao.FollowerDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    @author Ilya Moiseenko on 1.10.23
*/
public class JdbcFollowerDao implements FollowersDao {

    private static JdbcFollowerDao instance;

    private final String INSERT = "insert into \"followers\" (parent_id, child_id) values (?, ?)";
    private final String SELECT_BY_PARENT_AND_CHILD = "select * from \"followers\" where parent_id = ? and child_id = ?";
    private final String DELETE_BY_PARENT_AND_CHILD = "delete from \"followers\" where parent_id = ? and child_id = ?";
    private final String GET_FOLLOWERS_BY_USER = "select count (*) from \"followers\" where child_id = ?";
    private final String GET_FOLLOWING_BY_USER = "select count (*) from \"followers\" where parent_id = ?";

    private JdbcFollowerDao() {}

    public static JdbcFollowerDao getInstance() {
        if (instance == null)
            instance = new JdbcFollowerDao();

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

    @Override
    public int getFollowersByUser(User user) {
        int countOfFollowers = 0;

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_FOLLOWERS_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                countOfFollowers = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countOfFollowers;
    }

    @Override
    public int getFollowingByUser(User user) {
        int countOfFollowing = 0;

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_FOLLOWING_BY_USER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                countOfFollowing = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countOfFollowing;
    }
}
