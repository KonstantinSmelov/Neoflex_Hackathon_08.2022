package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text_of_question")
    private String textOfQuestion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<WrongAnswer> wrongAnswer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> Answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

}
