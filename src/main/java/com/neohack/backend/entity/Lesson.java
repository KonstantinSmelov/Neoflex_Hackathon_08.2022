package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lessons", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private String data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "lesson")
    private Statistic statistic;
}
