package mk.ukim.finki.emt.library.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String surname;

    @ManyToOne
    Country country;

    public Author() {

    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
