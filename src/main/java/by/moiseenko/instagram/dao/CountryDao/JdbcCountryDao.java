package by.moiseenko.instagram.dao.CountryDao;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCountryDao implements CountryDao {

    private static JdbcCountryDao instance;

    private final String SELECT_ALL = "select * from \"country\"";
    private final String SELECT_BY_ID = "select * from \"country\" where id = ?";

    private JdbcCountryDao() {}

    public static JdbcCountryDao getInstance() {
        if (instance == null)
            return new JdbcCountryDao();

        return instance;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                Country country = Country
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public Optional<Country> findById(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Country country = Country
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

                return Optional.of(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
