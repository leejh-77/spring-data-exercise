create schema if not exists test;

create table if not exists test.books
(
    id          bigserial primary key not null,
    name        varchar(100)          not null,
    description text
);
alter table test.books owner to jonghoon;
