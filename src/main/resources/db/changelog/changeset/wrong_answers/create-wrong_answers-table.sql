create table neohack.wrong_answers
(
    id      bigserial,
    wrong_answer varchar(45),
    question_id bigserial references neohack.questions(id),
    primary key (id)
);