create table auto(
    id serial primary key, 
    name text,
	places smallint,
	cargo boolean	
);

insert into auto(name, places, cargo) values('Kamaz', 3, true);
insert into auto(name, places, cargo) values('Opel Cadett', 5, false);
insert into auto(name, places, cargo) values('Lada Largus', 7, false);
select * from auto;

update auto set places = '2' where name = 'Kamaz';
select * from auto;

delete from auto;
select * from auto;