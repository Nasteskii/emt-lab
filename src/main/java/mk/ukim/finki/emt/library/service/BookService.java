package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);

    Optional<Book> findAllByName(String name);

    Book save(String name, Category category, List<Long> authors, Integer availableCopies);

    Book save(Book book);

    Book edit(Long id, String name, Category category, List<Long> authors, Integer availableCopies);


    void deleteById(Long id);
}
