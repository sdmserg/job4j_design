create table student_courses(
id serial primary key,
student_id int REFERENCES students(id),
course_id int REFERENCES courses(id)
);