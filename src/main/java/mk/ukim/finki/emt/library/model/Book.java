package mk.ukim.finki.emt.library.model;

import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Category;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Category category;

    @ManyToMany
    List<Author> authors;

    Integer availableCopies;

    public Book() {

    }

    public Book(String name, Category category, List<Author> authors, Integer availableCopies) {
    }
}
