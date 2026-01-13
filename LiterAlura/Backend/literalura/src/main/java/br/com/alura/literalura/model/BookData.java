package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> categories,
        @JsonAlias("authors") List<AuthorData> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("summaries") List<String> summary,
        @JsonAlias("download_count") Integer downloads) {
}
