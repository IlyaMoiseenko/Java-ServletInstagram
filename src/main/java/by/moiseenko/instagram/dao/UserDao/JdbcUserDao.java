package by.moiseenko.instagram.dao.UserDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.City;
import by.moiseenko.instagram.entity.Country;
import by.moiseenko.instagram.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcUserDao implements UserDao<Integer> {

    private static JdbcUserDao instance;

    private final String INSERT = "insert into \"human\" (name, surname, username, photo, email, password, country_id, city_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_USERNAME = "select * from \"human\" join \"country\" on \"human\".country_id = \"country\".id join \"city\" on \"human\".city_id = \"city\".id where \"human\".username = ?";
    private final String UPDATE = "update \"human\" set name = ?, email = ?, password = ?, photo = ? where id = ?";

    private JdbcUserDao() {}

    public static JdbcUserDao getInstance() {
        if (instance == null)
            return new JdbcUserDao();

        return instance;
    }

    @Override
    public Integer add(User user) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setBytes(4, Base64.getDecoder().decode(user.getPhoto()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getCountry().getId());
            preparedStatement.setInt(8, user.getCity().getId());

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
    public Optional<User> getByUsername(String username) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = buildUserEntityFromResultSet(resultSet);

                return Optional.of(user);
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void update(User newUser, User currentUser) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setBytes(4, Base64.getDecoder().decode(newUser.getPhoto()));
            preparedStatement.setInt(5, currentUser.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User buildUserEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = User
                .builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .surname(resultSet.getString(3))
                .username(resultSet.getString(4))
                .photo(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                .email(resultSet.getString(6))
                .password(resultSet.getString(7))
                .build();

        Country country = Country
                .builder()
                .id(resultSet.getInt(10))
                .name(resultSet.getString(11))
                .build();

        City city = City
                .builder()
                .id(resultSet.getInt(12))
                .name(resultSet.getString(13))
                .build();

        city.setCountry(country);

        user.setCountry(country);
        user.setCity(city);

        return user;
    }
}
