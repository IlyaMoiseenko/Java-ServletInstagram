package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.model.City;
import by.moiseenko.instagram.storage.CityStorage.CityStorage;
import by.moiseenko.instagram.storage.CityStorage.JdbcCityStorage;

import java.util.List;
import java.util.Optional;

public class CityService {

    private static CityService instance;

    private final CityStorage cityStorage = new JdbcCityStorage();

    public static CityService getInstance() {
        if (instance == null)
            return new CityService();

        return instance;
    }

    public List<City> findAll() {
        return cityStorage.findAll();
    }

    public Optional<City> findById(int id) {
        return cityStorage.findById(id);
    }
}
