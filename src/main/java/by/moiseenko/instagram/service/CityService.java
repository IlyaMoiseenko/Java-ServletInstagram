package by.moiseenko.instagram.service;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import by.moiseenko.instagram.entity.City;
import by.moiseenko.instagram.dao.CityDao.CityDao;
import by.moiseenko.instagram.dao.CityDao.JdbcCityDao;

import java.util.List;
import java.util.Optional;

public class CityService {

    private static CityService instance;

    private final CityDao<Integer> cityDao = JdbcCityDao.getInstance();

    private CityService() {}

    public static CityService getInstance() {
        if (instance == null)
            return new CityService();

        return instance;
    }

    public Iterable<City> findAll() {
        return cityDao.findAll();
    }

    public Optional<City> findById(Integer id) {
        return cityDao.findById(id);
    }
}
