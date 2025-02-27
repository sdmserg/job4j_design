CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Иван', 'Петров', 25, 'Россия'),
('Алексей', 'Смирнов', 25, 'Беларусь'),
('Мария', 'Иванова', 28, 'Казахстан'),
('Ольга', 'Сидорова', 35, 'Россия'),
('Дмитрий', 'Кузнецов', 40, 'Украина'),
('Татьяна', 'Васильева', 32, 'Россия'),
('Сергей', 'Попов', 27, 'Грузия');

select * from customers
where age = (select min(age)
             from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES
(1000, 1),
(1500, 2),
(2000, 3),
(1200, 4),
(800, 5),
(1100, 1),
(1750, 2),
(2200, 3);

select * from customers c
where c.id not in (select customer_id
                   from orders);