package br.com.alura.forumHub.domain.topic;

import br.com.alura.forumHub.domain.course.Course;
import br.com.alura.forumHub.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record TopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long courseId) {
}
