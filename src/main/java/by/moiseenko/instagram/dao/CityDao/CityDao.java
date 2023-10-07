package by.moiseenko.instagram.dao.CityDao;

import by.moiseenko.instagram.entity.City;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CityDao<ID> {

    Iterable<City> findAll();
    Optional<City> findById(ID id);
}
