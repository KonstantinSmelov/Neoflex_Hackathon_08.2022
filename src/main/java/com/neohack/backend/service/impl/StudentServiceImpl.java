package com.neohack.backend.service.impl;

import com.neohack.backend.exception.NoElementException;
import lombok.RequiredArgsConstructor;
import com.neohack.backend.dao.StudentRepository;
import com.neohack.backend.entity.Student;
import com.neohack.backend.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void createStudent(Student student) {
        log.debug("createStudent(): получен student: {}", student);
        studentRepository.save(student);
        log.debug("createStudent(): student {} создан!", student);
    }

    @Override
    public Student getById(Long id) throws NoElementException {
        return studentRepository.findById(id).orElseThrow(() -> new NoElementException(String.format("Студента с id [%d] не существует!", id)));
    }

}
