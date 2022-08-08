create table neohack.courses
(
    id      bigserial,
    name varchar(45),
    teacher_id bigserial references neohack.teachers(id),
    student_id bigserial references neohack.students(id),
    primary key (id)
);