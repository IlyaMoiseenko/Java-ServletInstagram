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

public class JdbcCountryDao implements CountryDao<Integer> {

    // Fields
    private static JdbcCountryDao instance;

    private final String SELECT_ALL = "select * from \"country\"";
    private final String SELECT_BY_ID = "select * from \"country\" where id = ?";

    // Constructors
    private JdbcCountryDao() {}

    // Methods
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
                Country country = buildCountryEntityFromResultSet(resultSet);

                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public Optional<Country> findById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Country country = buildCountryEntityFromResultSet(resultSet);

                return Optional.of(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private Country buildCountryEntityFromResultSet(ResultSet resultSet) throws SQLException {

        return Country
                .builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .build();
    }
}
