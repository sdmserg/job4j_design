create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

BEGIN TRANSACTION;
UPDATE products SET price = price + 10 WHERE name = 'product_1';
SELECT * FROM products;
SAVEPOINT sp1;
UPDATE products SET count = count - 2 WHERE name = 'product_2';
SELECT * FROM products;
ROLLBACK TO SAVEPOINT sp1;
SELECT * FROM products;
SAVEPOINT sp2;
UPDATE products SET producer = 'producer_updated' WHERE name = 'product_3';
SELECT * FROM products;
ROLLBACK TO SAVEPOINT sp1;
SELECT * FROM products;
COMMIT;