package br.com.alura.forumHub.domain.course;

public record CourseDetail(
        Long id,
        String name,
        Category category
) {
    public CourseDetail (Course course){
        this(course.getId(), course.getName(), course.getCategory());
    }
}
