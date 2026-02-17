package br.com.alura.forumHub.domain.topic;

import br.com.alura.forumHub.domain.answer.Answer;
import br.com.alura.forumHub.domain.course.Course;
import br.com.alura.forumHub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Topic")
@Table(name="topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Column (name="created_at")
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="course_id")
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    public Topic(@Valid TopicDTO topicDTO, User author, Course course) {
        this.title = topicDTO.title();
        this.message = topicDTO.message();
        this.createdAt = LocalDateTime.now();
        this.author = author;
        this.course = course;
    }

    public void update(@Valid TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() != null) {
            this.title = topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null) {
            this.message = topicUpdateDTO.message();
        }
        if(topicUpdateDTO.status() != null) {
            this.status = topicUpdateDTO.status();
        }
    }

    public void exclude() {
        this.status = Status.INATIVO;
    }
}
