create table book (
    id int primary key,
    name varchar(100) not null,
    genre varchar(100) not null
);

create table movie (
    id int primary key,
    name varchar(100) not null,
    description varchar(100) not null
)