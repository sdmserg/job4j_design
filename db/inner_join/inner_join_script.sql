create table roles (
id serial primary key,
role_name varchar(30) UNIQUE
);

create table users (
id serial primary key,
user_name VARCHAR(50) UNIQUE,
role_id int REFERENCES roles(id)
);

INSERT INTO roles(role_name) VALUES ('System Administrator');
INSERT INTO roles(role_name) VALUES ('Software Developer');
INSERT INTO roles(role_name) VALUES ('DevOps Engineer');
INSERT INTO roles(role_name) VALUES ('Database Administrator');
INSERT INTO roles(role_name) VALUES ('QA Engineer');
INSERT INTO roles(role_name) VALUES ('Technical Support');
INSERT INTO roles(role_name) VALUES ('Network Engineer');

INSERT INTO users(user_name, role_id) VALUES ('Иван Смирнов', 1);
INSERT INTO users(user_name, role_id) VALUES ('Алексей Петров', 2);
INSERT INTO users(user_name, role_id) VALUES ('Мария Иванова', 3);
INSERT INTO users(user_name, role_id) VALUES ('Дмитрий Сидоров', 4);
INSERT INTO users(user_name, role_id) VALUES ('Екатерина Орлова', 5);
INSERT INTO users(user_name, role_id) VALUES ('Сергей Кузнецов', 6);
INSERT INTO users(user_name, role_id) VALUES ('Ольга Васильева', 7);

select r.role_name, u.user_name
from roles as r inner join users as u on r.id = u.role_id;

select r.role_name as "Имя пользователя", u.user_name as "Роль пользователя"
from roles as r inner join users as u on r.id = u.role_id;

select r.role_name "Имя пользователя", u.user_name Роль
from roles r inner join users u on r.id = u.role_id;