INSERT INTO user (id, first_name, last_name, email, password) VALUES (1, 'Memory', 'Not Found', 'rauan.ru@gmail.com', '$2a$10$iLMfZCrw/YLyun0d2XyBTeipaeMIJaQl/srzNxi400u7v3zPbYa7W');

INSERT INTO role (id, name) VALUES (1, 'ROLE_PASSENGER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO role (id, name) VALUES (3, 'ROLE_EMPLOYEE');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);

insert into train (train_id, number) values (1, "004Ц");
insert into train (train_id, number) values (2, "807");
insert into train (train_id, number) VALUES (3, "803");

insert into carriage (id, number, type, actual_seats, train_id) values (1, 1, "kupe", 10, 1);
insert into carriage (id, number, type, actual_seats, train_id) values (2, 2, "plackart", 10, 1);


insert into day (id, name) values (1, "M");
insert into day (id, name) values (2, "T");
insert into day (id, name) values (3, "W");
insert into day (id, name) values (4, "R");
insert into day (id, name) values (5, "F");
insert into day (id, name) values (6, "S");
insert into day (id, name) values (7, "SD");


insert into train (train_id, number) values (1, "004Ц");
insert into train (train_id, number) values (2, "807");
insert into train (train_id, number) VALUES (3, "803");

insert into carriage (carriage_id, type, actual_seats, train_id, number) values (1, "kupe", 10, 1, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (2, "plackart", 10, 3, 2);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (3, "plackart", 10, 2, 1);

insert into seats(number, carriage_id) values (1, 1);
insert into seats(number, carriage_id) values (2, 1);
insert into seats(number, carriage_id) values (3, 1);
insert into seats(number, carriage_id) values (4, 1);

insert into seats(number, carriage_id) values (1, 2);
insert into seats(number, carriage_id) values (2, 2);
insert into seats(number, carriage_id) values (3, 2);
insert into seats(number, carriage_id) values (4, 2);

insert into day (id, name) values (1, "M");
insert into day (id, name) values (2, "T");
insert into day (id, name) values (3, "W");
insert into day (id, name) values (4, "R");
insert into day (id, name) values (5, "F");
insert into day (id, name) values (6, "S");
insert into day (id, name) values (7, "SD");

insert into type (id) values (1);
insert into type (id) values (2);

insert into type_day (day_id, type_id) values (1, 1);
insert into type_day (day_id, type_id) values (3, 1);
insert into type_day (day_id, type_id) values (5, 1);
insert into type_day (day_id, type_id) values (7, 1);

insert into type_day (day_id, type_id) values (2, 2);
insert into type_day (day_id, type_id) values (4, 2);
insert into type_day (day_id, type_id) values (6, 2);

insert into schedule (id, train_id, type_id) VALUES (1, 1, 1);
insert into schedule (id, train_id, type_id) VALUES (2, 3, 1);
insert into schedule (id, train_id, type_id) VALUES (3, 2, 1);

insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Nurly-Zhol", "13:52:00", "17:16:00", 1, 1);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Karagandy", "19:52:00", "20:02:00", 1, 1);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("AAA", "20:21:00", "20:25:00", 1, 1);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Dariya", "20:54:00", "20:56:00", 1, 1);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Akadyr", "21:51:00", "21:54:00", 1, 1);

insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Train1Nurly-Zhol", null, "17:16:00", 1, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Karagandy", "19:52:00", "20:02:00", 1, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Dariya", "20:54:00", "20:56:00", 1, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Akadyr", "21:51:00", "21:54:00", 1, 2);

insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Sary-Shagan", "00:34:00", "00:44:00", 2, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Shu", "03:49:00", "04:19:00", 2, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Otar", "06:05:00", "06:08:00", 2, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Almaty1", "07:52:00", "07:57:00", 2, 2);
insert into station (name, arr_time, dep_time, day_num, schedule_id) values ("Almaty2", "08:15:00", null, 2, 2);

