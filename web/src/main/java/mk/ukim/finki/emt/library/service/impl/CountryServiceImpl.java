package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findAllByName(String name) {
        return countryRepository.findAllByName(name);
    }

    @Override
    public Country save(String name, String continent) {
        Country country = new Country(name, continent);
        return countryRepository.save(country);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).get();
        country.setName(name);
        country.setContinent(continent);
        return country;
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
