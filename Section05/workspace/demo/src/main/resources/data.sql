insert into course(id, fullname, created_date, last_updated_date) values(10001, 'JPA in 50 steps', current_timestamp(), current_timestamp());
insert into course(id, fullname, created_date, last_updated_date) values(10002, 'Spring in 50 steps', current_timestamp(), current_timestamp());
insert into course(id, fullname, created_date, last_updated_date) values(10003, 'Spring Boot in 100 steps', current_timestamp(), current_timestamp());

insert into passport(id, number) values(40001, 'E123456');
insert into passport(id, number) values(40002, 'N123457');
insert into passport(id, number) values(40003, 'L126453');

insert into student(id, name, passport_id) values(20001, 'Pil', 40001);
insert into student(id, name, passport_id) values(20002, 'Hong', 40002);
insert into student(id, name, passport_id) values(20003, 'Feel', 40003);

insert into review(id, rating, description, course_id) values(50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id) values(50002, '4', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id) values(50003, '5', 'Awesome Course', 10003);