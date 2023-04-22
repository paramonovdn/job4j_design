insert into fauna(name, avg_age, discovery_date) values('chameleon', 900, '1816-06-30');
insert into fauna(name, avg_age, discovery_date) values('elephant', 3650, '1566-01-01');
insert into fauna(name, avg_age, discovery_date) values('beaked whale', 10950, '2021-05-01');
insert into fauna(name, avg_age, discovery_date) values('white shark', 12500, '1957-06-30');
insert into fauna(name, avg_age, discovery_date) values('hummingbird', 1600, '1960-11-12');

select * from fauna where name like '%fish%'; 

select * from fauna where avg_age >= 10000 and avg_age <= 21000 ; 

select * from fauna where discovery_date is null; 

select * from fauna where discovery_date < '1950-01-01'; 