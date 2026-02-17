package br.com.alura.forumHub.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicUpdateDTO(
        @NotNull
        Long id,
        String title,
        String message,
        Status status
) {
}
