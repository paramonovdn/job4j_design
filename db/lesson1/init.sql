create table role(
    id serial primary key,
    name text
);

create table users(
    id serial primary key,
    name text,
	role_id int references role(id)
);

create table rules(
    id serial primary key,
    name text
);

create table role_rules(
    id serial primary key,    
    role_id int references role(id),
    rules_id int references rules(id)
);

create table category(
    id serial primary key,
    type text
);

create table state(
    id serial primary key,
    status text
);

create table item(
    id serial primary key,
    text text,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments(
    id serial primary key,
    text text,
	item_id int references item(id)
);

create table attach(
    id serial primary key,
    file bytea,
	item_id int references item(id)
);