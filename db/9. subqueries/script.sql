-- TASK №1:

CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES('Ivan', 'Ivanov', 18, 'Russia');
INSERT INTO customers (first_name, last_name, age, country) VALUES('Petr', 'Sidorov', 32, 'Russia');
INSERT INTO customers (first_name, last_name, age, country) VALUES('John', 'Malchovich', 70, 'USA');
INSERT INTO customers (first_name, last_name, age, country) VALUES('Radj', 'Capur', 40, 'India');
INSERT INTO customers (first_name, last_name, age, country) VALUES('James', 'Mcavoy', 45, 'Irish');

SELECT first_name, last_name, age FROM customers WHERE age = (SELECT MIN(age) FROM customers);



-- TASK №2:

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO orders(amount, customer_id) VALUES(1000, 1); 
INSERT INTO orders(amount, customer_id) VALUES(2500, 2);
INSERT INTO orders(amount, customer_id) VALUES(500, 5);

SELECT first_name, last_name FROM customers WHERE customers.id NOT IN (SELECT customer_id FROM orders);