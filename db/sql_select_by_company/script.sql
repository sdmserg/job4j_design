CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES
(1, 'VK'),
(2, 'Sber'),
(3, 'Huawei'),
(4, 'Yandex'),
(5, 'Yadro');

INSERT INTO person (id, name, company_id) VALUES
(1, 'Иван Петров', 1),
(2, 'Мария Сидорова', 1),
(3, 'Алексей Смирнов', 1),
(4, 'Елена Кузнецова', 1),
(5, 'Дмитрий Иванов', 1),
(6, 'Ольга Васильева', 1),
(7, 'Николай Попов', 1),
(8, 'Сергей Орлов', 2),
(9, 'Анна Морозова', 2),
(10, 'Максим Волков', 2),
(11, 'Татьяна Белова', 2),
(12, 'Павел Федоров', 2),
(13, 'Наталья Сергеева', 2),
(14, 'Игорь Никитин', 2),
(15, 'Виктор Ефимов', 3),
(16, 'Юлия Андреева', 3),
(17, 'Олег Захаров', 3),
(18, 'Алина Дмитриева', 3),
(19, 'Артем Григорьев', 3),
(20, 'Ксения Лебедева', 3),
(21, 'Валерий Романов', 4),
(22, 'Екатерина Тимофеева', 4),
(23, 'Руслан Соловьев', 4),
(24, 'Людмила Павлова', 4),
(25, 'Георгий Богданов', 4),
(26, 'Зоя Крылова', 5),
(27, 'Анатолий Голубев', 5),
(28, 'Светлана Мельникова', 5),
(29, 'Роман Сафонов', 5);

select p.name, c.name
from person p
join company c on p.company_id = c.id
where c.id != 5;

select c.name, COUNT(p.name)
from company c join person p on p.company_id = c.id
group by c.name
having count(p.name) = (
    select max(number)
    from (
        select COUNT(p.name) number
        from company c
        join person p
        on p.company_id = c.id
        group by c.name)
);