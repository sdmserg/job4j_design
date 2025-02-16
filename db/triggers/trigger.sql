create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
    UPDATE products
    SET price = price + price * 0.10
    WHERE id IN (SELECT id from inserted);
    return NEW;
    END;
$$
language 'plpgsql';

create trigger after_tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

INSERT INTO products (name, producer, count, price)
VALUES
('Product A', 'Producer A', 10, 100),
('Product B', 'Producer B', 3, 200),
('Product C', 'Producer C', 8, 300);

create or replace function before_tax()
    returns trigger as
$$
    BEGIN
    NEW.price = NEW.price + NEW.price * 0.25;
    return NEW;
    END;
$$
language 'plpgsql';

create trigger before_tax_trigger
before insert
on products
for each row
execute procedure before_tax();

INSERT INTO products (name, producer, count, price)
VALUES ('Product D', 'Producer D', 5, 1000),
       ('Product E', 'Producer E', 10, 2000);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function insert_history_of_price()
    returns trigger as
$$
    BEGIN
    INSERT INTO history_of_price(name, price ,date)
    values (NEW.name, NEW.price, now());
    return NEW;
    END;
$$
language 'plpgsql';

create trigger trigger_history_of_price
after insert
on products
for each row
execute procedure insert_history_of_price();

INSERT INTO products (name, producer, count, price)
VALUES ('Product F', 'Producer F', 7, 1000),
       ('Product G', 'Producer G', 15, 5000);