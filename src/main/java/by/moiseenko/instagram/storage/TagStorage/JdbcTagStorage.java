package by.moiseenko.instagram.storage.TagStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.Hashtag;
import by.moiseenko.instagram.model.Post;

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

public class JdbcTagStorage implements TagStorage {

    private static JdbcTagStorage instance;

    private final String INSERT = "insert into \"hashtag\" (name) values (?)";
    private final String FIND_BY_NAME = "select * from \"hashtag\" where name = ?";
    private final String FIND_ALL_BY_POST = "select * from \"hashtag\" join \"post_hashtag\" on \"hashtag\".id = \"post_hashtag\".hashtag_id where \"post_hashtag\".post_id = ?";

    private JdbcTagStorage() {}

    public static JdbcTagStorage getInstance() {
        if (instance == null)
            instance = new JdbcTagStorage();

        return instance;
    }

    @Override
    public void save(Hashtag hashtag) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, hashtag.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Hashtag> findByName(String name) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Hashtag hashtag = Hashtag
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

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
                Hashtag hashtag = Hashtag.builder().name(resultSet.getString(2)).build();

                hashtags.add(hashtag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashtags;
    }
}
