package by.moiseenko.instagram.service;

import by.moiseenko.instagram.model.Country;
import by.moiseenko.instagram.storage.CountryStorage.CountryStorage;
import by.moiseenko.instagram.storage.CountryStorage.JdbcCountryStorage;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class CountryService {

    private static CountryService instance;

    private final CountryStorage countryStorage = new JdbcCountryStorage();

    public static CountryService getInstance() {
        if (instance == null)
            return new CountryService();

        return instance;
    }

    public List<Country> findAll() {
        return countryStorage.findAll();
    }

    public Optional<Country> findById(int id) {
        return countryStorage.findById(id);
    }
}
