package br.com.alura.literalura.model;

public record BookDataListDTO(Long id,
                              String title,
                              String authors,
                              String subject,
                              String sumary) {
    public BookDataListDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthors().toString(), book.getSubjects(), book.getSummary());
    }
}
