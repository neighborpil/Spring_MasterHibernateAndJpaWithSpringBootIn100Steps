insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10001, 'JPA in 50 steps', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10002, 'Spring in 50 steps', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10003, 'Spring Boot in 100 steps', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10004, 'Dummy1', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10005, 'Dummy2', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10006, 'Dummy3', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10007, 'Dummy4', current_timestamp(), current_timestamp(), false);
insert into course(id, fullname, created_date, last_updated_date, is_deleted) values(10008, 'Dummy5', current_timestamp(), current_timestamp(), false);

insert into passport(id, number) values(40001, 'E123456');
insert into passport(id, number) values(40002, 'N123457');
insert into passport(id, number) values(40003, 'L126453');

insert into student(id, name, passport_id) values(20001, 'Pil', 40001);
insert into student(id, name, passport_id) values(20002, 'Hong', 40002);
insert into student(id, name, passport_id) values(20003, 'Feel', 40003);

insert into review(id, rating, description, course_id) values(50001, 'FIVE', 'Great Course', 10001);
insert into review(id, rating, description, course_id) values(50002, 'FOUR', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id) values(50003, 'FIVE', 'Awesome Course', 10003);

insert into student_course(student_id, course_id) values(20001,10001);
insert into student_course(student_id, course_id) values(20002,10001);
insert into student_course(student_id, course_id) values(20003,10001);
insert into student_course(student_id, course_id) values(20001,10003);