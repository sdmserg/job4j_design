create table fauna (
id serial primary key,
name text,
avg_age int,
discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Тигр', 7300, '1738-01-01');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Лев',	6570, '1818-02-10');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Черепаха', 36500, '1820-03-14');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Рыбка', 1825,	'1400-04-20');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Собака', 5110, '1288-05-23');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Слон', 25550, '1097-06-16');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Волк', 3650, '1529-07-12');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Лиса', 4015, '1353-08-11');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('Медведь', 10950, '1117-09-08');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';