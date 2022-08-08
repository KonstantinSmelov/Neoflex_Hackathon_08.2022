create table neohack.users_roles
(
    user_id bigserial,
    roles varchar,
    foreign key (user_id) references neohack.users (id)
);

insert into neohack.users_roles (user_id, roles) values (1, 'ROLE_ADMIN' );