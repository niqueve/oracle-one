package br.com.alura.forumHub.controller;

import br.com.alura.forumHub.domain.course.CourseDTO;
import br.com.alura.forumHub.domain.course.CourseDetail;
import br.com.alura.forumHub.domain.course.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity createCourse(@RequestBody @Valid CourseDTO courseDTO, UriComponentsBuilder uriBuilder) {
        var course = courseService.createCourse(courseDTO);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(uri).body(new CourseDetail(course));
    }
}
