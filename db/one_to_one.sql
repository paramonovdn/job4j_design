create table car(
    id serial primary key,
    body_number int
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table people_cars(
    id serial primary key,
    car_id int references car(id) unique,
    people_id int references people(id) unique
);