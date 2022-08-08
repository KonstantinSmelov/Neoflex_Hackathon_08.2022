package com.neohack.backend.service;

import com.neohack.backend.entity.Student;
import com.neohack.backend.entity.Teacher;
import com.neohack.backend.exception.NoElementException;

import java.util.List;

public interface TeacherService {
    void createTeacher(Teacher teacher);
    Teacher getById(Long id) throws NoElementException;
    List<Teacher> getByName(String name) throws NoElementException;
}
