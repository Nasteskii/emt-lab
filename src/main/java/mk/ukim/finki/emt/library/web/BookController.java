package mk.ukim.finki.emt.library.web;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.service.AuthorService;
import mk.ukim.finki.emt.library.service.BookService;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
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
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
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
    public String deleteProduct(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Country> countries = this.countryService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("countries", countries);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/books?error=ProductNotFound";
    }

    @GetMapping("/add-form")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("manufacturers", authors);
        model.addAttribute("categories", countries);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam List<Long> authors,
            @RequestParam Integer availableCopies) {
        if (id != null) {
            this.bookService.edit(id, name, category, authors, availableCopies);
        } else {
            this.bookService.save(name, category, authors, availableCopies);
        }
        return "redirect:/books";
    }

}
