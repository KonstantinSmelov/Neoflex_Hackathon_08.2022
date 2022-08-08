create table neohack.statistics
(
    id      bigserial,
    sum_correct_answers smallint,
    is_lesson_read boolean,
    lesson_id bigserial references neohack.lessons(id),
    test_id bigserial references neohack.tests(id),
    primary key (id)
);