select c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name as body_name
from car_bodies cb
left join cars c on c.body_id = cb.id
where c.name is null;

select ce.name as engine_name
from car_engines ce
left join cars c on c.engine_id = ce.id
where c.name is null;

select ct.name as transmission_name
from car_transmissions ct
left join cars c on c.transmission_id = ct.id
where c.name is null;