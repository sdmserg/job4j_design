create table type (
id serial primary key,
name varchar(100);
);

create table product(
id serial primary key,
name text,
expired_date date,
price float,
type_id int references type(id)
);


INSERT INTO type (id, name) VALUES (1, 'ФРУКТЫ'), (2, 'ОВОЩИ'), (3, 'МЯСО');
INSERT INTO type (id, name) VALUES (4, 'МОЛОКО'), (5, 'ХЛЕБ'), (6, 'НАПИТКИ');
INSERT INTO type (id, name) VALUES (7, 'КОНДИТЕРСКИЕ'), (8, 'КРУПЫ');
INSERT INTO type (id, name) VALUES (9, 'РЫБА'), (10, 'СЫР');


INSERT INTO product (name, expired_date, price, type_id) VALUES
('Яблоко', '2025-02-13', 50.00, 1),
('Банан', '2025-02-06', 60.00, 1),
('Апельсин', '2025-02-18', 80.00, 1);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Картофель', '2025-03-10', 25.00, 2),
('Морковь', '2025-02-23', 30.00, 2),
('Огурец', '2025-02-07', 40.00, 2),
('Томат', '2025-02-15', 55.00, 2);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Говядина', '2025-02-15', 450.00, 3),
('Свинина', '2025-02-05', 400.00, 3);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Молоко', '2025-02-03', 80.00, 4),
('Сметана', '2025-02-18', 90.00, 4),
('Творог', '2025-02-06', 120.00, 4),
('Йогурт', '2025-02-15', 70.00, 4),
('Ванильное мороженое', '2025-02-12', 150.00, 4);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Хлеб', '2025-02-07', 35.00, 5),
('Булочка', '2025-02-11', 25.00, 5),
('Круассан', '2025-02-10', 45.00, 5),
('Багет', '2025-02-09', 50.00, 5);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Чай', '2026-02-08', 150.00, 6),
('Кофе', '2027-02-08', 350.00, 6);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Шоколад', '2025-08-08', 200.00, 7),
('Печенье', '2025-05-08', 120.00, 7),
('Торт', '2025-02-07', 500.00, 7),
('Конфеты', '2026-02-08', 250.00, 7),
('Вафли', '2025-07-08', 130.00, 7);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Рис', '2026-02-08', 90.00, 8),
('Гречка', '2026-02-08', 95.00, 8);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Сёмга', '2025-02-07', 700.00, 9),
('Тунец', '2025-02-13', 650.00, 9),
('Скумбрия', '2025-02-11', 400.00, 9);

INSERT INTO product (name, expired_date, price, type_id) VALUES
('Чеддер', '2025-02-18', 350.00, 10),
('Моцарелла', '2025-02-20', 400.00, 10);


select p.name
from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

select name from product
where name LIKE '%мороженое%';

select name from product
where expired_date < current_date;

select name from product
where price in (select max(price) from product);

select t.name, count(p.name) from type t
join product p on p.type_id = t.id
group by t.name;

select p.name from product p
join type t on p.type_id = t.id
where t.name IN ('СЫР', 'МОЛОКО');

select t.name from product p
join type t on t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name, t.name from product p
join type t on t.id = p.type_id;