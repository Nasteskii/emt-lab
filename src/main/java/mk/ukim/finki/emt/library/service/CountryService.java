package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);

    Optional<Country> findAllByName(String name);

    Country save(String name, String continent);

    Country save(Country country);

    Country edit(Long id, String name, String continent);

    void deleteById(Long id);
}
