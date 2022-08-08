create table neohack.answers
(
    id      bigserial,
    answer varchar(45),
    question_id bigserial references neohack.questions(id),
    primary key (id)
);