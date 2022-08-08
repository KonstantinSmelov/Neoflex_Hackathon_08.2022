package com.neohack.backend.service.impl;

import com.neohack.backend.dao.CourseRepository;
import com.neohack.backend.entity.Course;
import com.neohack.backend.entity.Student;
import com.neohack.backend.entity.Teacher;
import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.service.CourseService;
import com.neohack.backend.service.StudentService;
import com.neohack.backend.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Override
    public void createCourse(Course course, String teacherName) throws NoElementException {
        log.debug("createCourse(): получен course: {}", course);
        course.setTeacher(teacherService.getByName(teacherName).get(0));
        course.setStudent(studentService.getById(1L));
        courseRepository.save(course);
        log.debug("ServiceImpl createCourse(): course {} создан!", course);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
