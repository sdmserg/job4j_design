create table departments (
id serial primary key,
name varchar(100)
);

create table employees (
id serial primary key,
name varchar(100)
department_id int references departments(id)
);


INSERT INTO departments(name) VALUES ('Отдел продаж'), ('Отдел маркетинга'), ('Отдел разработки');
INSERT INTO departments(name) VALUES ('Отдел HR'), ('Финансовый отдел');
INSERT INTO departments(name) VALUES ('Юридический отдел'), ('Отдел поддержки');

INSERT INTO employees (name, department_id) VALUES ('Иван Иванов', 1), ('Петр Петров', 1);
INSERT INTO employees (name, department_id) VALUES ('Анна Смирнова', 2), ('Елена Васильева', 3);
INSERT INTO employees (name, department_id) VALUES ('Максим Сидоров', 3), ('Тимур Кузнецов', 4);
INSERT INTO employees (name, department_id) VALUES ('Ольга Попова', 4), ('Алексей Беляев', 5);

select * from employees e
left join departments d on e.department_id = d.id;

select * from employees e
right join departments d on e.department_id = d.id;

select * from employees e
cross join departments d on e.department_id = d.id;

select d.name from departments d
left join employees e on d.id = e.department_id
where e.name is null;


select e.name, d.name from employees e
left join departments d on e.department_id = d.id
where d.id = 3;

select e.name, d.name from departments d
right join employees e on e.department_id = d.id
where d.id = 3;

create table teens (
id serial primary key,
name varchar(50),
gender varchar(20)
);

INSERT INTO teens (name, gender) VALUES ('Алексей Смирнов', 'мужской'), ('Иван Петров', 'мужской');
INSERT INTO teens (name, gender) VALUES ('Дмитрий Васильев', 'мужской'), ('Сергей Кузнецов', 'мужской');
INSERT INTO teens (name, gender) VALUES ('Артём Сидоров', 'мужской'), ('Мария Иванова', 'женский');
INSERT INTO teens (name, gender) VALUES ('Анна Попова', 'женский'), ('Екатерина Беляева', 'женский');
INSERT INTO teens (name, gender) VALUES ('Ольга Тихонова', 'женский'), ('Наталья Орлова', 'женский');

select * from teens m
cross join teens f
where m.gender != f.gender and m.gender != 'женский';