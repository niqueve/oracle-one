package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.BookDataListDTO;
import br.com.alura.literalura.model.BookDetailDTO;
import br.com.alura.literalura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("livros")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping
    public ResponseEntity<Page<BookDataListDTO>> list(@PageableDefault(size = 10) Pageable pageable) {
        var page = booksRepository.findAll(pageable).map(BookDataListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<BookDataListDTO>> top5(@PageableDefault(size = 10) Pageable pageable) {
        var top5List = booksRepository.findTop5ByOrderByDownloadsDesc();
        return ResponseEntity.ok(top5List.stream().map(b -> new BookDataListDTO(b)).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDetailDTO> findById(@PathVariable Long id) {
        var book = booksRepository.getReferenceById(id);
        return ResponseEntity.ok(new BookDetailDTO(book));
    }
}
