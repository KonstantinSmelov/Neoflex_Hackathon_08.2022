create table neohack.questions
(
    id      bigserial,
    text_of_question varchar(500),
    test_id bigserial references neohack.tests(id),
    primary key (id)
);