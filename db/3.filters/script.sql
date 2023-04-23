create table type(
    id serial primary key,
    name text    
);

create table product(
    id serial primary key,
	name text,
    type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('МОЛОКО'), ('СЫР'), ('КОЛБАСА'); 

insert into product(name, type_id, expired_date, price) values('Молоко Село Луговое', 1, '2023-05-15', 92.50);
insert into product(name, type_id, expired_date, price) values('Молоко Домик в деревне', 1, '2023-05-20', 99.50);
insert into product(name, type_id, expired_date, price) values('Молоко Яшкино', 1, '2023-05-10', 85.50);

insert into product(name, type_id, expired_date, price) values('Cыр плавленный', 2, '2023-06-15', 70.50);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 2, '2023-06-20', 150.50);
insert into product(name, type_id, expired_date, price) values('Сыр гауда', 2, '2023-06-10', 125.25);

insert into product(name, type_id, expired_date, price) values('Колбаса копченая', 3, '2023-10-15', 270.50);
insert into product(name, type_id, expired_date, price) values('Колбаса докторская', 3, '2023-10-20', 350.50);
insert into product(name, type_id, expired_date, price) values('Колбаса диетическая', 3, '2023-10-10', 325.25);

insert into product(name, type_id, expired_date, price) values('Мороженное пломбир', 1, '2023-12-15', 100);

select p.name as "Наименование продукта" , t.name Тип, p.expired_date as "Срок годности", p.price Цена from product as p join type as t on p.type_id = t.id where t.name like '%СЫР%';

select * from product where lower (name) like '%мороженное%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(p.type_id) from type as t join product as p on  t.id = p.type_id group by t.name;

select p.name as "Наименование продукта" , t.name Тип, p.expired_date as "Срок годности", p.price Цена from product as p join type as t on p.type_id = t.id where t.name like '%СЫР%' or t.name like '%МОЛОКО%';

select t.name, count(p.type_id) from type as t join product as p on  t.id = p.type_id group by t.name having count(p.type_id) < 10;

select p.name as "Наименование продукта" , t.name Тип, p.expired_date as "Срок годности", p.price Цена from product as p join type as t on p.type_id = t.id;