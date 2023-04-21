package mk.ukim.finki.emt.library.web;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.service.AuthorService;
import mk.ukim.finki.emt.library.service.BookService;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/books", "/"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;


    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String deleteBook(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent()) {
            this.bookService.deleteById(id);
            return "redirect:/books";
        }
        return "redirect:/books?error=Product Not Found";
    }

    @PostMapping("/get-book/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        if (this.bookService.getBook(id) == -1) {
            return "redirect:/books?error=No Available Copies";
        }
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String editBook(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Country> countries = this.countryService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("categories", Category.values());
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/books?error=Product Not Found";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", Category.values());
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam List<Long> authors,
            @RequestParam String availableCopies) {
        if (id != null) {
            this.bookService.edit(id, name, category, authors, Integer.parseInt(availableCopies));
        } else {
            this.bookService.save(name, category, authors, Integer.parseInt(availableCopies));
        }
        return "redirect:/books";
    }

}
