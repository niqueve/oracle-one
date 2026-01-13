package br.com.alura.literalura.model;

public record AuthorListDTO(Long id,
                            String name,
                            Integer birthYear,
                            Integer deathYear) {
    public AuthorListDTO (Author author){
        this(author.getId(), author.getName(), author.getBirthYear(), author.getDeathYear());
    }
}
