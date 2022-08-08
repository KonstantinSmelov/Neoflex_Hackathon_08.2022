package com.neohack.backend.dao;

import com.neohack.backend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT *" +
            "FROM neohack.teachers " +
            "JOIN neohack.users " +
            "ON neohack.teachers.id = neohack.users.id " +
            "WHERE neohack.users.username = :teacherName"
            , nativeQuery = true)
    List<Teacher> findByName(@Param("teacherName") String teacherName);

}
