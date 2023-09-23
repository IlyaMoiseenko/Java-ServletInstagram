package by.moiseenko.instagram.storage.CountryStorage;

import by.moiseenko.instagram.model.Country;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CountryStorage {

    List<Country> findAll();
    Optional<Country> findById(int id);
}
