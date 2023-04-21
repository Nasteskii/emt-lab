package mk.ukim.finki.emt.library.web.rest;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthorsPage() {
        return this.authorService.findAll();
    }
}
