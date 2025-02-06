create table students (
id serial primary key,
full_name varchar(50),
birth_day date,
phone_number varchar(20),
group_id int REFERENCES groups(id),
student_book_id int REFERENCES student_books(book_number) UNIQUE
);