package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Test> tests;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Lesson> lessons;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
