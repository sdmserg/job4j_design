create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO devices (name, price) VALUES ('Ноутбук MSI', 120000), ('iPhone Apple', 90000), ('Планшет Samsung', 50000);
INSERT INTO devices (name, price) VALUES ('Монитор LG', 30000), ('Наушники Sony', 15000), ('Клавиатура Logitech', 7000);
INSERT INTO devices (name, price) VALUES ('Мышь Razer', 6000), ('Телевизор Samsung', 55000), ('Колонки JBL', 10000);
INSERT INTO devices (name, price) VALUES ('Смарт-часы Huawei', 25000), ('Игровая приставка PlayStation 5', 75000);
INSERT INTO devices (name, price) VALUES ('Фотоаппарат Canon', 85000), ('Видеокарта NVIDIA RTX 4080', 180000);
INSERT INTO devices (name, price) VALUES ('Системный блок HP', 65000), ('Электронная книга PocketBook', 20000);

INSERT INTO people (name) VALUES ('Иван Петров'), ('Мария Смирнова'), ('Алексей Сидоров');
INSERT INTO people (name) VALUES ('Елена Козлова'), ('Дмитрий Иванов'), ('Ольга Федорова');
INSERT INTO people (name) VALUES ('Павел Васильев'), ('Светлана Морозова'), ('Антон Кузнецов');
INSERT INTO people (name) VALUES ('Татьяна Беляева'), ('Владимир Соловьев'), ('Юлия Крылова');
INSERT INTO people (name) VALUES ('Артем Никитин'), ('Екатерина Орлова'), ('Сергей Максимов');

INSERT INTO devices_people (device_id, people_id) VALUES (1, 1), (2, 2), (3, 3), (4, 4);
INSERT INTO devices_people (device_id, people_id) VALUES (5, 5), (6, 6), (7, 7), (8, 8);
INSERT INTO devices_people (device_id, people_id) VALUES (9, 9), (10, 10), (11, 11), (12, 12);
INSERT INTO devices_people (device_id, people_id) VALUES (13, 13), (14, 14), (15, 15), (1, 6);
INSERT INTO devices_people (device_id, people_id) VALUES (2, 7), (3, 8), (4, 9), (5, 10),  (6, 11);
INSERT INTO devices_people (device_id, people_id) VALUES (7, 12), (8, 13), (9, 14), (10, 15);

select avg(price) from devices;

select p.name, avg(d.price)
from people p
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price)
from people p
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;