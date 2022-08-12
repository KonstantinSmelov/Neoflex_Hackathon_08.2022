package com.neohack.backend.controller;

import com.neohack.backend.dto.CourseDto;
import com.neohack.backend.entity.Course;
import com.neohack.backend.mapper.CourseMapper;
import com.neohack.backend.service.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.neohack.backend.exception.NoElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    public final CourseService courseService;

    @ApiOperation(value = "Создание нового курса преподавателем")
    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto, Principal principal) throws NoElementException {
        log.debug("createCourse(): получен courseDto: {}", courseDto);
        Course course = CourseMapper.INSTANCE.toCourse(courseDto);
        courseService.createCourse(course, principal.getName());
        log.debug("createCourse(): courseDto замаплен в course: {}", course);
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }
}
