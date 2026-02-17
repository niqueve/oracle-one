package br.com.alura.forumHub.domain.topic;

import br.com.alura.forumHub.domain.ForumHubValidationException;
import br.com.alura.forumHub.domain.course.CourseRepository;
import br.com.alura.forumHub.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Topic createTopic(TopicDTO topicDTO) {
        if (topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCase(topicDTO.title(), topicDTO.message())){
            throw new ForumHubValidationException("Tópico já existe");
        }
        var author = userRepository.getReferenceById(topicDTO.userId());
        var course = courseRepository.getReferenceById(topicDTO.courseId());
        var topic = new Topic(topicDTO, author, course);
        topicRepository.save(topic);
        return topic;
    }

    public Page<TopicListDTO> listAllTopics(Pageable pageable) {
        var page = topicRepository.findAll(pageable).map(TopicListDTO::new);
        return page;
    }

    public Topic getTopicDetail(Long id) {
        return topicRepository.getReferenceById(id);
    }

    @Transactional
    public Topic updateTopic(@Valid TopicUpdateDTO topicUpdateDTO) {
        var topic = topicRepository.findById(topicUpdateDTO.id())
                        .orElseThrow(() -> new ForumHubValidationException("Tópico não encontrado"));

        topic.update(topicUpdateDTO);
        return topic;
    }

    @Transactional
    public void deleteTopic(Long id) {
        var topic = topicRepository.findById(id);
        if(topic.isPresent()) {
            topicRepository.delete(topic.get());
        }
    }
}
