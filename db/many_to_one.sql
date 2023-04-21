create table book(
    id serial primary key,
    name varchar(255)
);

create table authors(
    id serial primary key,
    name varchar(255),
    position_id int references book(id)
);

