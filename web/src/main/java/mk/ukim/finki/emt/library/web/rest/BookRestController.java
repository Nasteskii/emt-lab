package mk.ukim.finki.emt.library.web.rest;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBookPage() {
        return this.bookService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<List<Book>> deleteBook(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent()) {
            this.bookService.deleteById(id);
            if (this.bookService.findById(id).isEmpty()) {
                return ResponseEntity.ok().body(this.bookService.findAll());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<String> getBook(@PathVariable Long id) {
        if (this.bookService.getBook(id) == -1) {
            return ResponseEntity.badRequest().body("No available copies");
        }
        return ResponseEntity.ok().body("You got the book");
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
