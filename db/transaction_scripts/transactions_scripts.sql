CREATE TABLE cars (
id serial primary key,
brand varchar(50),
model varchar(50),
year integer
);

INSERT INTO cars (brand, model, year) VALUES
('Toyota', 'Corolla', 2018),
('Honda', 'Civic', 2020),
('Ford', 'Focus', 2017),
('Chevrolet', 'Malibu', 2019),
('Nissan', 'Altima', 2021),
('BMW', '3 Series', 2016),
('Mercedes-Benz', 'C-Class', 2022);

#В 1-ом окне:
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT avg(year) from cars;
UPDATE cars SET year = 2023 WHERE brand = 'Toyota';

#Во втором окне
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;;
SELECT avg(year) from cars;
UPDATE cars SET year = 2021 WHERE brand = 'Honda';
COMMIT;

#В 1-ом окне
COMMIT;