create table neohack.lessons
(
    id      bigserial,
    name varchar(45),
    data varchar(10000),
    course_id bigserial references neohack.courses(id),
    primary key (id)
);