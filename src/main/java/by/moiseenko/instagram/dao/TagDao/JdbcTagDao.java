package by.moiseenko.instagram.dao.TagDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Hashtag;
import by.moiseenko.instagram.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 4.10.23
*/

public class JdbcTagDao implements TagDao<Integer> {

    private static JdbcTagDao instance;

    private final String INSERT = "insert into \"hashtag\" (name) values (?)";
    private final String FIND_BY_NAME = "select * from \"hashtag\" where name = ?";
    private final String FIND_ALL_BY_POST = "select * from \"hashtag\" join \"post_hashtag\" on \"hashtag\".id = \"post_hashtag\".hashtag_id where \"post_hashtag\".post_id = ?";
    private final String SAVE_FOR_POST = "insert into \"post_hashtag\" (hashtag_id, post_id) values (?, ?)";

    private JdbcTagDao() {}

    public static JdbcTagDao getInstance() {
        if (instance == null)
            instance = new JdbcTagDao();

        return instance;
    }

    @Override
    public Integer save(Hashtag hashtag) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, hashtag.getName());

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
    public Optional<Hashtag> findByName(String name) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Hashtag hashtag = buildHashtagEntityFromResultSet(resultSet);

                return Optional.of(hashtag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Hashtag> findAllByPost(Post post) {
        List<Hashtag> hashtags = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_POST);
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hashtag hashtag = buildHashtagEntityFromResultSet(resultSet);

                hashtags.add(hashtag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashtags;
    }

    @Override
    public Optional<Integer> saveForPost(Hashtag hashtag, Post post) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FOR_POST);
            preparedStatement.setInt(1, hashtag.getId());
            preparedStatement.setInt(2, post.getId());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private Hashtag buildHashtagEntityFromResultSet(ResultSet resultSet) throws SQLException {

        return Hashtag
                .builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .build();
    }
}
