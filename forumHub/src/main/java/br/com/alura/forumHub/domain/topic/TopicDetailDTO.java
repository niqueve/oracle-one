package br.com.alura.forumHub.domain.topic;


import java.time.LocalDateTime;

public record TopicDetailDTO(
        String title,
        String message,
        LocalDateTime createdAt,
        Status status,
        Long userId,
        Long courseId
        ) {

    public TopicDetailDTO(Topic topic) {
        this (topic.getTitle(), topic.getMessage(), topic.getCreatedAt(),topic.getStatus(),topic.getAuthor().getId(), topic.getCourse().getId());
    }
}
