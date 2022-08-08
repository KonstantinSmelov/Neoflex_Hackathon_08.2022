package com.neohack.backend.service.impl;

import com.neohack.backend.dao.TeacherRepository;
import com.neohack.backend.entity.Teacher;
import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public void createTeacher(Teacher teacher) {
        log.debug("createTeacher(): получен teacher: {}", teacher);
        teacherRepository.save(teacher);
        log.debug("createTeacher(): teacher {} создан!", teacher);
    }

    @Override
    public Teacher getById(Long id) throws NoElementException {
        return teacherRepository.findById(id).orElseThrow(() -> new NoElementException(String.format("Учителя с id [%d] не существует!", id)));
    }

    @Override
    public List<Teacher> getByName(String name) {
        return teacherRepository.findByName(name);
    }
}
