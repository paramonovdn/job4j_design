create table car_bodies(
	id serial primary key,
	name varchar(255)
); 

create table car_engines(
	id serial primary key,
	name varchar(255)
); 

create table car_transmissions(
	id serial primary key,
	name varchar(255)
); 

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
); 

insert into car_bodies(name) values('седан'),('хэтчбек'),('пикап');
insert into car_engines(name) values('jdi 2.0'),('vaz 124'),('aj-v6');
insert into car_transmissions(name) values('automatic'),('mechanical'),('robot');

insert into cars(name, body_id, engine_id, transmission_id) values('jaguar', 1, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('lada priora', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('tesla', 1, null, 1);

select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name from cars c left join car_bodies cb on c.body_id = cb.id left join car_engines ce on c.engine_id = ce.id left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name body_name from cars c right join car_bodies cb on c.body_id = cb.id where c.body_id is null;

select ce.name engine_name from cars c right join car_engines ce on c.engine_id = ce.id where c.engine_id is null;	

select ct.name transmission_name from cars c right join car_transmissions ct on c.transmission_id = ct.id where c.transmission_id is null; 