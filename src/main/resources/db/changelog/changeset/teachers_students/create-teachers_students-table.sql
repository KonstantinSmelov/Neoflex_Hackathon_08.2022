create table neohack.teachers_students
(
    student_id bigserial references neohack.students(id),
    teacher_id bigserial references neohack.teachers(id),
    primary key (student_id, teacher_id)
);