package by.moiseenko.instagram.storage.CityStorage;

import by.moiseenko.instagram.model.City;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CityStorage {

    List<City> findAll();
    Optional<City> findById(int id);
}
