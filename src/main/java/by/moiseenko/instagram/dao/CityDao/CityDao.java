package by.moiseenko.instagram.dao.CityDao;

import by.moiseenko.instagram.entity.City;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CityDao {

    List<City> findAll();
    Optional<City> findById(int id);
}
