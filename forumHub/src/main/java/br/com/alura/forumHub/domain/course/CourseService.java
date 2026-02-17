package br.com.alura.forumHub.domain.course;

import br.com.alura.forumHub.domain.ForumHubValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Course createCourse (CourseDTO courseDTO) {
        if(courseRepository.existsByName(courseDTO.name())){
            throw new ForumHubValidationException("Curso já cadastrado");
        }

        var  course = new Course(courseDTO);
        courseRepository.save(course);
        return course;
    }
}
