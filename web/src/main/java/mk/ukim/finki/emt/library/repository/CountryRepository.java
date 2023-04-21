package mk.ukim.finki.emt.library.repository;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findAllByName(String name);
}
