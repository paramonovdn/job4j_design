insert into role(name) values('user');
insert into users(name, role_id) values('Ivanov Igor', 1);
insert into rules(name) values('only read');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into category(type) values('error correction');
insert into state(status) values('stopped');
insert into item(text, user_id, category_id, state_id) values('The calculation form does not work in release 2.2', 1, 3, 3);
insert into comments(text, item_id) values('In the "calculation" section', 6);
insert into attach(file, item_id) values(E'\\000', 6);


