package br.com.alura.forumHub.controller;

import br.com.alura.forumHub.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity createTopic(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriBuilder) {
        var topic = topicService.createTopic(topicDTO);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDetailDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> getAllTopics(@PageableDefault(size=5, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var page = topicService.listAllTopics(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailTopic(@PathVariable Long id){
        var topic = topicService.getTopicDetail(id);
        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }

    @PutMapping
    public ResponseEntity updateTopic(@RequestBody @Valid TopicUpdateDTO topicUpdateDTO) {
        var topic = topicService.updateTopic(topicUpdateDTO);

        return ResponseEntity.ok(new TopicDetailDTO(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id){
        topicService.deleteTopic(id);

        return ResponseEntity.noContent().build();
    }

}
