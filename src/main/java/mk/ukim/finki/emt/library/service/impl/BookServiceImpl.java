package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.BookRepository;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country country = new Country("Macedonia", "Europe");
        countryRepository.save(country);
        Author author1 = new Author("Author 1", "Author 1", country);
        Author author2 = new Author("Author 2", "Author 2", country);
        authorRepository.save(author1);
        authorRepository.save(author2);
        Book book = new Book("A", Category.CLASSICS, List.of(author1,author2), 3);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findAllByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book save(String name, Category category, List<Long> authorsIds, Integer availableCopies) {
        List<Author> authors = authorRepository.findAllById(authorsIds);
        Book book = new Book(name, category, authors, availableCopies);
        return bookRepository.save(book);
    }


    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String name, Category category, List<Long> authorsIds, Integer availableCopies) {
        List<Author> authors = authorRepository.findAllById(authorsIds);
        Book book = bookRepository.findById(id).get();
        book.setName(name);
        book.setCategory(category);
        book.setAuthors(authors);
        book.setAvailableCopies(availableCopies);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public int getBook(Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        List<Author> authors = authorRepository.findAllById(bookDto.getAuthors());
        Book book = bookRepository.findById(id).get();
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthors(authors);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        List<Author> authors = authorRepository.findAllById(bookDto.getAuthors());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), authors, bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }
}
