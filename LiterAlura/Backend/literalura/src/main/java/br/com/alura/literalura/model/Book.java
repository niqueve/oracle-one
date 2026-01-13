package br.com.alura.literalura.model;

import br.com.alura.literalura.service.translate.MyMemoryTranslate;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private long id;
    @Column(unique = true, columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String subjects;
    private String languages;
    @Column(columnDefinition = "TEXT")
    private String summary;
    private Integer downloads;
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {}

    public Book(BookData bookData) {
        this.id = bookData.id();
        this.title = bookData.title();
        this.subjects = String.join(",",bookData.subjects());
        this.authors = bookData.authors().stream().map(Author::new).collect(Collectors.toList());
        this.languages = String.join(",", bookData.languages());
        this.summary = MyMemoryTranslate.gettranslation(String.join(" ", bookData.summary()));
        this.category = Category.fromlist(bookData.categories());
        this.downloads = bookData.downloads();
    }

    @Override
    public String toString() {
        return "Título "+ title + "\n"+
                "Autor: "+ authors + "\n"+
                "Categoria: "+ category + "\n"+
                "línguas: "+ languages + "\n"+
                "resumo: "+ summary;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getLanguages() {
        return languages;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public Category getCategory() {
        return category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
