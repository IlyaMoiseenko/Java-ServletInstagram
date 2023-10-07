package by.moiseenko.instagram.dao.CityDao;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.entity.City;
import by.moiseenko.instagram.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class JdbcCityDao implements CityDao<Integer> {

    // Fields
    private static JdbcCityDao instance;

    private final String SELECT_ALL = "select * from \"city\" join \"country\" on \"city\".country_id = \"country\".id";
    private final String SELECT_BY_ID = "select * from \"city\" join \"country\" on \"city\".country_id = \"country\".id where \"city\".id = ?";

    // Constructors
    private JdbcCityDao() {}

    // Methods
    public static JdbcCityDao getInstance() {
        if (instance == null)
            return new JdbcCityDao();

        return instance;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                City city = City
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();

                // getting country for city
                Country country = Country
                        .builder()
                        .id(resultSet.getInt(4))
                        .name(resultSet.getString(5))
                        .build();

                city.setCountry(country);

                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public Optional<City> findById(Integer id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                City city = buildCityEntityFromResultSet(resultSet);

                return Optional.of(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private City buildCityEntityFromResultSet(ResultSet resultSet) throws SQLException {
        City city = City
                .builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .build();

        // getting country for city
        Country country = Country
                .builder()
                .id(resultSet.getInt(4))
                .name(resultSet.getString(5))
                .build();

        city.setCountry(country);

        return city;
    }
}
