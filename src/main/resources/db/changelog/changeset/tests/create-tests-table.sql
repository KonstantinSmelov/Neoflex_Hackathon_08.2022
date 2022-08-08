create table neohack.tests
(
    id      bigserial,
    name varchar(45),
    course_id bigserial references neohack.courses(id),
    primary key (id)
);