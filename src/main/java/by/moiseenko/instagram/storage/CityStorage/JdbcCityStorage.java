package by.moiseenko.instagram.storage.CityStorage;

import by.moiseenko.instagram.config.JdbcConnection;
import by.moiseenko.instagram.model.City;
import by.moiseenko.instagram.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class JdbcCityStorage implements CityStorage {

    private static CityStorage instance;

    private final String SELECT_ALL = "select * from \"city\" join \"country\" on \"city\".country_id = \"country\".id";
    private final String SELECT_BY_ID = "select * from \"city\" join \"country\" on \"city\".country_id = \"country\".id where \"city\".id = ?";

    private JdbcCityStorage() {}

    public static CityStorage getInstance() {
        if (instance == null)
            return new JdbcCityStorage();

        return instance;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();

        try (Connection connection = JdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));

                // getting country for city
                Country country = new Country();
                country.setId(resultSet.getInt(4));
                country.setName(resultSet.getString(5));

                city.setCountry(country);

                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public Optional<City> findById(int id) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));

                // getting country for city
                Country country = new Country();
                country.setId(resultSet.getInt(4));
                country.setName(resultSet.getString(5));

                city.setCountry(country);

                return Optional.of(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
