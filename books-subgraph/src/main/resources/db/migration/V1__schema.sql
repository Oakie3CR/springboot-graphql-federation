create table book (
    id int primary key generated always as identity,
    name varchar(100) not null,
    genre varchar(100) not null,
    author_id int not null
);
