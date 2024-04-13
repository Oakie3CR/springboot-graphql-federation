create table author (
    id int primary key generated always as identity,
    name varchar(100) not null
);
