package br.com.alura.forumHub.domain.topic;

import java.time.LocalDateTime;

public record TopicListDTO(
        String title,
        String message,
        Long userId,
        Long courseId,
        LocalDateTime createdAt,
        Status status
) {
    public TopicListDTO(Topic topic){
        this (topic.getTitle(), topic.getMessage(), topic.getAuthor().getId(),topic.getCourse().getId(), topic.getCreatedAt(), topic.getStatus());
    }
}
