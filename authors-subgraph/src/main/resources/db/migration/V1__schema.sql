create table author (
    id int primary key generated always as identity,
    name varchar(100) not null
);

create table book (
    book_id int primary key,
    author_id int not null,
    foreign key (author_id) references author(id)
);
