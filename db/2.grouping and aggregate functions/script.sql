insert into devices(name, price) values('Xiaomi', 9500), ('Nokia', 20000), ('Iphone_14', 80000);
insert into people(name) values('Ivanov I.I.'), ('Petrov P.P.'), ('Sidorov S.S.');
insert into devices_people(device_id, people_id) values(1, 1), (2, 3), (3, 2);
insert into devices_people(device_id, people_id) values(1, 1), (1, 2), (1, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d 
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d 
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;