package by.moiseenko.instagram.storage.UserStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.City;
import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcUserStorage implements UserStorage {

    private static JdbcUserStorage instance;

    private final String INSERT = "insert into \"human\" (name, surname, username, photo, email, password, country_id, city_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_USERNAME = "select * from \"human\" join \"country\" on \"human\".country_id = \"country\".id join \"city\" on \"human\".city_id = \"city\".id where \"human\".username = ?";

    private JdbcUserStorage() {}

    public static JdbcUserStorage getInstance() {
        if (instance == null)
            return new JdbcUserStorage();

        return instance;
    }

    @Override
    public void add(User user) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Base64.getEncoder().encodeToString(resultSet.getBytes(5)),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );

                Country country = new Country();
                country.setId(resultSet.getInt(10));
                country.setName(resultSet.getString(11));

                City city = new City();
                city.setId(resultSet.getInt(12));
                city.setName(resultSet.getString(13));
                city.setCountry(country);

                user.setCountry(country);
                user.setCity(city);

                return Optional.of(user);
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return Optional.empty();
    }
}
