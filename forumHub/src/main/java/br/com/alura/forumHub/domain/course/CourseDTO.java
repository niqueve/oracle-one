package br.com.alura.forumHub.domain.course;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
        @NotBlank
        String name,
        @NotNull
        @Enumerated(EnumType.STRING)
        Category category
) {
}
