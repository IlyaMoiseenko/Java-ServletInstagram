package by.moiseenko.instagram.dao.CountryDao;

import by.moiseenko.instagram.entity.Country;

import java.util.Optional;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public interface CountryDao<ID> {

    Iterable<Country> findAll();
    Optional<Country> findById(ID id);
}
