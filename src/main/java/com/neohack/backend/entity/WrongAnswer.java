package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wrong_answers", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WrongAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "wrong_answer")
    private String wrong_answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;
}
