package com.neohack.backend.mapper;

import com.neohack.backend.dto.CourseDto;
import com.neohack.backend.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDto courseDto);
}
