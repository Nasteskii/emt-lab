package mk.ukim.finki.emt.library.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Category;

import java.util.List;

@Data
public class BookDto {
    private String name;

    private Category category;

    private List<Long> authors;

    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, List<Long> authors, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authors = authors;
        this.availableCopies = availableCopies;
    }
}
