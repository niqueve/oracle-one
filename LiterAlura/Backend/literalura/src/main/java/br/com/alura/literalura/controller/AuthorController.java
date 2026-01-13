package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.AuthorDetailDTO;
import br.com.alura.literalura.model.AuthorListDTO;
import br.com.alura.literalura.model.BookSummaryDTO;
import br.com.alura.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autores")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<Page<AuthorListDTO>> list(@PageableDefault(size = 10) Pageable pageable) {
        var page = authorRepository.findAll(pageable).map(AuthorListDTO::new);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDetailDTO> findById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map( a -> {
                    var bookDTO = a.getBooks().stream()
                        .map(b -> new BookSummaryDTO(b.getTitle(), b.getCategory()))
                        .toList();
                    return ResponseEntity.ok( new AuthorDetailDTO(
                            a.getName(),
                            a.getBirthYear(),
                            a.getDeathYear(),
                            bookDTO));
        })
                .orElse(ResponseEntity.notFound().build());
    }

}
