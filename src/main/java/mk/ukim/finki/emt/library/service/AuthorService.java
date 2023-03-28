package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);

    Optional<Author> findAllByNameOrSurname(String name, String surname);

    Author save(String name, String surname, Country country);

    Author save(Author author);

    Author edit(Long id, String name, String surname, Country country);


    void deleteById(Long id);

}
