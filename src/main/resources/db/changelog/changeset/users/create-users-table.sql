create table neohack.users
(
    id   bigserial,
    username  varchar unique not null,
    password  varchar(100) not null,
    email     varchar unique not null,
    is_active boolean,
    date_of_birth date,
    last_activity date,
    primary key (id)
);

insert into neohack.users (username, password, email, is_active) values ('admin', '$2a$08$B0VGEgR5b3MHPWDv4YXBouxlWWmDxYb9VejvNDiXJwF49hBz9GPim', 'admin@mail.ru', 'true' );