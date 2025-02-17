create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price) values
('Смартфон Galaxy A52', 'Samsung', 10, 30000),
('Смартфон iPhone 13', 'Apple', 5, 80000),
('Ноутбук ThinkPad X1', 'Lenovo', 0, 120000),
('Ноутбук MacBook Air', 'Apple', 4, 110000),
('Планшет Galaxy Tab S7', 'Samsung', 7, 45000),
('Планшет iPad Pro', 'Apple', 6, 90000),
('Смарт-часы Galaxy Watch', 'Samsung', 15, 15000),
('Смарт-часы Apple Watch', 'Apple', 0, 30000),
('Монитор UltraSharp', 'Dell', 12, 25000),
('Монитор Pro Display XDR', 'Apple', 2, 400000),
('Игровая консоль PS5', 'Sony', 0, 60000),
('Игровая консоль Xbox Series X', 'Microsoft', 0, 55000),
('Наушники AirPods Pro', 'Apple', 10, 20000),
('Наушники Galaxy Buds', 'Samsung', 11, 12000),
('Внешний накопитель T7', 'Samsung', 20, 8000);

create
or replace procedure delete_row(d_id integer, d_count integer)
language 'plpgsql'
as $$
    BEGIN
	    IF d_id > 0 THEN
		    delete from products
		    where id = d_id;
		END IF;
        IF d_count = 0 THEN
            delete from products
            where count = 0;
        END IF;
	END;
$$;

call delete_row(1, 10);
call delete_row(0, 0);

create
or replace function f_delete_row(d_id integer, d_price integer)
returns void
language 'plpgsql'
as $$
    BEGIN
	    IF d_id > 0 THEN
		    delete from products
		    where id = d_id;
        END IF;
        IF d_price = 20000 THEN
            delete from products
            where price < d_price;
        END IF;
	END;
$$;

select f_delete_row(7, 0);
select f_delete_row(0, 20000);