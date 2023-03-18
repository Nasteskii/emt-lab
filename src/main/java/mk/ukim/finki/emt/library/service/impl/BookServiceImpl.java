package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.BookRepository;
import mk.ukim.finki.emt.library.service.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
}
