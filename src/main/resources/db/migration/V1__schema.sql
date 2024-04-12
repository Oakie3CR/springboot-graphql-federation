create table author (
    id int primary key generated always as identity,
    name varchar(100) not null
);

create table book (
    id int primary key generated always as identity,
    name varchar(100) not null,
    genre varchar(100) not null,
    author_id int not null,
    foreign key (author_id) references author(id)
);
