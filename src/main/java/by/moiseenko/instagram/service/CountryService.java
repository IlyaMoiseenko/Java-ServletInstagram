package by.moiseenko.instagram.service;

import by.moiseenko.instagram.entity.Country;
import by.moiseenko.instagram.dao.CountryDao.CountryDao;
import by.moiseenko.instagram.dao.CountryDao.JdbcCountryDao;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class CountryService {

    private static CountryService instance;

    private final CountryDao<Integer> countryDao = JdbcCountryDao.getInstance();

    private CountryService() {}

    public static CountryService getInstance() {
        if (instance == null)
            return new CountryService();

        return instance;
    }

    public Iterable<Country> findAll() {
        return countryDao.findAll();
    }

    public Optional<Country> findById(Integer id) {
        return countryDao.findById(id);
    }
}
