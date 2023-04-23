create table departments(
    id serial primary key,
    name varchar(255)
    
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values('department_1'), ('department_2'), ('department_3');
insert into employees(name, department_id) values('Ivanov', 1), ('Sidorov', 3);

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;

select d.name from departments d left join  employees e on e.department_id = d.id where e.id is null;

select e.name Департамент, d.name Сотрудник from employees e left join departments d on e.department_id = d.id where e.id is not null;
select e.name Департамент, d.name Сотрудник from employees e right join departments d on e.department_id = d.id where e.id is not null;


create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values('Kate', 'girl'),('Nina', 'girl'),('Mike','boy'),('Joye', 'boy');

select t1.name, t2.name, (t1.name, t2.name) Pair from teens t1 cross join teens t2 where t1.gender like '%girl%' and t2.gender like '%boy%';
