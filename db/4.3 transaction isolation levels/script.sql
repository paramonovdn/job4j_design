create table auto(
    id serial primary key, 
    name text,
    places smallint,
    cargo boolean	
);

insert into auto(name, places, cargo) values('Kamaz', 3, true);
insert into auto(name, places, cargo) values('Opel Cadett', 5, false);
insert into auto(name, places, cargo) values('Lada Largus', 7, false);


--transaction1
begin transaction isolation level serializable;
select sum(places) from auto;
update auto set places = 12 where id = 3;
commit;

--transaction2
begin transaction isolation level serializable;
select sum(places) from auto;
update auto set places = 15 where places = 3;
commit;


