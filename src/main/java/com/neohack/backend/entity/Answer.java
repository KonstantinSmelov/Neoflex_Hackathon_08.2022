package com.neohack.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "neohack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;
}
