create table auto(
    id serial primary key, 
    name text,
    places smallint,
    cargo boolean	
);

insert into auto(name, places, cargo) values('Kamaz', 3, true);
insert into auto(name, places, cargo) values('Opel Cadett', 5, false);
insert into auto(name, places, cargo) values('Lada Largus', 7, false);

begin transaction;

savepoint update_savepoint;
update auto set places = 9 where name = 'Kamaz';

savepoint insert_savepoint;
insert into auto(name, places, cargo) values('Honda Accord', 5, false);
insert into auto(name, places, cargo) values('GAZ 2124', 5, false);

savepoint delete_savepoint;
delete from auto where name = 'Honda Accord';

rollback to savepoint delete_savepoint;
select * from auto;

rollback to savepoint insert_savepoint;
select * from auto;

rollback to savepoint update_savepoint;
select * from auto;

commit;




