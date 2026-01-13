package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(AuthorData authorData) {
        this.birthYear=authorData.birthYear();
        this.deathYear=authorData.deathYear();
        this.name=authorData.name();
    }

    @Override
    public String toString() {
        return "Nome: " + this.name + ", nascimento: " + this.birthYear + ", morte: " + this.deathYear;
    }

    public Long getId() {
        return id;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
