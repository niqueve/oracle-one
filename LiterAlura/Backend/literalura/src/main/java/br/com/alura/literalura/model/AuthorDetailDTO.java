package br.com.alura.literalura.model;

import java.util.List;

public record AuthorDetailDTO(String name,
                              Integer birthYear,
                              Integer deathYear,
                              List<BookSummaryDTO> books) {
}
