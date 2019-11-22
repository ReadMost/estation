
insert into train (train_id, number) values (1, "line1");
insert into train (train_id, number) values (2, "line2");
insert into train (train_id, number) VALUES (3, "line3");

insert into carriage (carriage_id, type, actual_seats, train_id, number) values (1, "kupe", 10, 1, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (2, "plackart", 10, 1, 2);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (3, "kupe", 10, 2, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (4, "plackart", 10, 2, 2);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (5, "kupe", 10, 3, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (6, "plackart", 10, 3, 2);

insert into seats(number, carriage_id) values (1, 1);
insert into seats(number, carriage_id) values (2, 1);
insert into seats(number, carriage_id) values (3, 1);
insert into seats(number, carriage_id) values (4, 1);

insert into seats(number, carriage_id) values (1, 2);
insert into seats(number, carriage_id) values (2, 2);
insert into seats(number, carriage_id) values (3, 2);
insert into seats(number, carriage_id) values (4, 2);

insert into seats(number, carriage_id) values (1, 3);
insert into seats(number, carriage_id) values (2, 3);
insert into seats(number, carriage_id) values (3, 3);
insert into seats(number, carriage_id) values (4, 3);

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
insert into schedule (id, train_id, type_id) VALUES (2, 1, 2);
insert into schedule (id, train_id, type_id) VALUES (3, 2, 1);
insert into schedule (id, train_id, type_id) VALUES (4, 2, 2);
insert into schedule (id, train_id, type_id) VALUES (5, 3, 1);
insert into schedule (id, train_id, type_id) VALUES (6, 3, 2);

--mainStation
insert into main_station(main_station_id, name) values (1, "Nur-Sultan");
insert into main_station(main_station_id, name) values (2, "Kokshetau");
insert into main_station(main_station_id, name) values (3, "Petropavl");

insert into main_station(main_station_id, name) values (4, "Kostanay");
insert into main_station(main_station_id, name) values (5, "Pavlodar");
--line 1 one
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (1, null, "08:00:00", 1, 1);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (2, "11:00:00", "11:05:00", 1, 1);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (3, "16:00:00", null, 1, 1);
--line 1 two
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (3, null, "17:00:00", 1, 2);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (2, "22:00:00", "22:05:00", 1, 2);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (1, "01:00:00", null, 2, 2);
--line 2 one
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (2, null, "12:00:00", 1, 3);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (4, "15:00:00", null, 1, 3);
--line 2 two
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (4, null, "16:00:00", 1, 4);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (2, "19:00:00", null, 1, 4);
--line 3 one
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (1, null, "12:00:00", 1, 5);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (5, "15:00:00", null, 1, 5);
--line 3 two
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (5, null, "19:00:00", 1, 6);
insert into station (main_station_id, arr_time, dep_time, day_num, schedule_id) values (1, "12:00:00", null, 1, 6);
