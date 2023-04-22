create table book(
    id serial primary key,
    name varchar(255),
    publication_year int
);

create table authors(
    id serial primary key,
    name varchar(255),
    birthday date,
    position_id int references book(id)
);


insert into book(name, publication_year) values('The Catcher in the Rye', 1951);
insert into book(name, publication_year) values('Fight club', 1999);
insert into book(name, publication_year) values('Arc de Triomphe', 1945);

insert into authors(name, birthday, position_id) values('David Salinger', '1919-01-01', 1);
insert into authors(name, birthday, position_id) values('Charles Michael Palahniuk', '1962-02-21', 2);
insert into authors(name, birthday, position_id) values('Erich Maria Remarque', '1898-06-22', 3);

select a.name Писатель, a.birthday as "Год рождения", b.name Книга, b.publication_year as "Год публикации" 
from authors as a join book as b on a.position_id = b.id;

select a.name Писатель, b.name Книга 
from authors as a join book as b on a.position_id = b.id;

select a.name Писатель, a.birthday as "Год рождения"
from authors as a join book as b on a.position_id = b.id where a.birthday < '1900-01-01';




