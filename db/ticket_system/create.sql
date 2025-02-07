create table roles (
id serial primary key,
role_name varchar(30) UNIQUE
);

create table users (
id serial primary key,
user_name VARCHAR(50) UNIQUE,
role_id int REFERENCES roles(id)
);

create table rules (
id serial primary key,
privilege varchar(20) UNIQUE
);

create table states (
id serial primary key,
state varchar(20) UNIQUE
);

create table categories (
id serial primary key,
category varchar(30) UNIQUE
);

create table items (
id serial primary key,
item_name varchar(50),
description varchar(100),
user_id int REFERENCES users(id),
category_id int REFERENCES categories(id),
state_id int REFERENCES states(id)
);

create table comments (
id serial primary key,
comment text,
item_id int REFERENCES items(id)
);

create table attachs (
id serial primary key,
attach_path varchar(100),
item_id int REFERENCES items(id)
);

create table roles_rules (
id serial primary key,
role_id int REFERENCES roles(id),
rule_id int REFERENCES rules(id)
);