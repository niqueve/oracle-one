package br.com.alura.forumHub.domain.topic;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCase(@NotBlank String title, @NotBlank String message);


    @Query("select t from Topic t where t.status = :status")
    Page<Topic> findAllByStatus(Pageable pageable, Status status);
}
