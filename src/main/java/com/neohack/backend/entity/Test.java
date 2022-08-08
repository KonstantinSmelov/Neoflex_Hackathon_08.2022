package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<Question> questions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "lesson")
    private Statistic statistic;
}
