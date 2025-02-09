INSERT INTO car_bodies (name) VALUES ('Седан'), ('Хэтчбек'), ('Пикап'), ('Купе'), ('Универсал');

INSERT INTO car_engines (name) VALUES ('1.6 бензин'), ('2.0 дизель'), ('Электро'), ('3.5 бензин'), ('2.5 гибрид');

INSERT INTO car_transmissions (name) VALUES ('Механика'), ('Автомат'), ('Вариатор'), ('Полуавтомат'), ('Робот');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Corolla', 1, 1, 1),
('Honda Civic', 2, 1, 2),
('Ford Ranger', 3, 2, 2),
('Nissan Leaf', 2, 3, 3),
('Mazda 6', 1, 2, 1),
('Hyundai Tucson', 3, 2, 2),
('Volkswagen Polo', 1, 1, 3),
('Kia Rio', 2, 1, 2),
('Subaru Outback', 2, 2, 1),
('Chevrolet Silverado', 3, 2, 3),
('Lada Granta', NULL, 1, 1),
('Tesla Model S', 1, NULL, 3),
('Ford Mustang', 1, 2, NULL),
('BMW X5', NULL, NULL, 2),
('Audi A4', 2, NULL, NULL),
('Mercedes-Benz GLE', NULL, 3, NULL);