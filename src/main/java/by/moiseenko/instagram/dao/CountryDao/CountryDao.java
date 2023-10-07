package by.moiseenko.instagram.dao.CountryDao;

import by.moiseenko.instagram.entity.Country;

import java.util.List;
import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CountryDao {

    List<Country> findAll();
    Optional<Country> findById(int id);
}
