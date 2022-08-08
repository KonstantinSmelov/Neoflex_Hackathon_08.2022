package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "statistics", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sum_correct_answers")
    private Short sumOfCorrectAnswers;

    @Column(name = "is_lesson_read")
    private Boolean isLessonRead;

    @OneToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;
}
