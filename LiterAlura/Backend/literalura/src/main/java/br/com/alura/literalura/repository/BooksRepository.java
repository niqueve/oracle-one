package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleIgnoreCase(String title);

    Optional<Book> findByTitleContainingIgnoreCase(String titlePiece);

    List<Book> findByLanguagesContainingIgnoreCase(String language);

    List<Book> findTop5ByOrderByDownloadsDesc();

}
