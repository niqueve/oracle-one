package br.com.alura.literalura.model;

public record BookDetailDTO(Long id,
                            String title,
                            String authors,
                            String subject,
                            String sumary,
                            String languages,
                            Integer downloads,
                            Category category) {


    public BookDetailDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthors().toString(), book.getSubjects(), book.getSummary(), book.getLanguages(), book.getDownloads(), book.getCategory());
    }
}
