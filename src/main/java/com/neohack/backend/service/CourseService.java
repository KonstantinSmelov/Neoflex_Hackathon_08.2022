package com.neohack.backend.service;

import com.neohack.backend.entity.Course;
import com.neohack.backend.exception.NoElementException;

import java.util.List;

public interface CourseService {
    void createCourse(Course course, String teacherName) throws NoElementException;
    List<Course> getCourses();
}
