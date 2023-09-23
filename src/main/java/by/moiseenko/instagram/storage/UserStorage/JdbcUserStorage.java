package by.moiseenko.instagram.storage.UserStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcUserStorage implements UserStorage {

    private static JdbcUserStorage instance;

    private final String INSERT = "insert into \"human\" (name, surname, username, photo, email, password, countryId, cityId) values (?, ?, ?, ?, ?, ?, ?, ?)";

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
}
