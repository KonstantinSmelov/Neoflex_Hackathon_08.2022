package com.neohack.backend.service;

import com.neohack.backend.entity.Student;
import com.neohack.backend.exception.NoElementException;

public interface StudentService {
    void createStudent(Student student);
    Student getById(Long id) throws NoElementException;

}
