create table cars(
id serial primary key,
model VArCHAr(30),
yaer int,
price DECIMAL(10, 2)
);
insert into cars(model, yaer, price) values ('BMV', 20019, 20000000.0);
update cars set model = 'audi', yaer = 2010, price = 500000.5;
delete from cars;