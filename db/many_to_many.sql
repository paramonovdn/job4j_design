 create table citizens(
     id serial primary key,
     name varchar(255)
 );
 
 create table flats(
     id serial primary key,
     number int
 );
 
 create table citizens_flats(
     id serial primary key,
     citizen_id int references citizens(id),
     flat_id int references flats(id)
 );
