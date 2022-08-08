package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private List<Student> students;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Course> courses;
}
