create table author (
    id int primary key generated always as identity,
    name varchar(100) not null
);

create table media_type (
    code char primary key,
    description varchar(20) not null
);

create table media (
    media_id int primary key,
    media_type_code char not null,
    author_id int not null,
    foreign key (author_id) references author(id),
    foreign key (media_type_code) references media_type(code)
);
