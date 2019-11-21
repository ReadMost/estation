
insert into train (train_id, number) values (1, "line1");
insert into train (train_id, number) values (2, "line2");
insert into train (train_id, number) VALUES (3, "line3");

insert into carriage (carriage_id, type, actual_seats, train_id, number) values (1, "kupe", 20, 1, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (4, "plackart", 20, 1, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (2, "kupe", 20, 2, 1);
insert into carriage (carriage_id, type, actual_seats, train_id, number) values (3, "kupe", 20, 3, 1);

insert into seats(number, carriage_id) values (1, 1);
insert into seats(number, carriage_id) values (2, 1);
insert into seats(number, carriage_id) values (3, 1);
insert into seats(number, carriage_id) values (4, 1);

insert into seats(number, carriage_id) values (1, 4);
insert into seats(number, carriage_id) values (2, 4);
insert into seats(number, carriage_id) values (3, 4);
insert into seats(number, carriage_id) values (4, 4);

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

insert into type_day (day_id, type_id) values (1, 1);
insert into type_day (day_id, type_id) values (3, 1);
insert into type_day (day_id, type_id) values (5, 1);
insert into type_day (day_id, type_id) values (7, 1);
insert into type_day (day_id, type_id) values (2, 1);
insert into type_day (day_id, type_id) values (4, 1);
insert into type_day (day_id, type_id) values (6, 1);

insert into schedule (id, train_id, type_id) VALUES (1, 1, 1);
insert into schedule (id, train_id, type_id) VALUES (2, 2, 1);
insert into schedule (id, train_id, type_id) VALUES (3, 3, 1);

 --mainStation
insert into main_station(main_station_id, name, longitude, latitude) values (1, "Nur-Sultan", 71.4704, 51.1605);
insert into main_station(main_station_id, name, longitude, latitude) values (2, "Kokshetau", 69.4044, 53.2871);
insert into main_station(main_station_id, name, longitude, latitude) values (3, "Petropavl", 69.1505, 54.8732);
insert into main_station(main_station_id, name, longitude, latitude) values (4, "Kostanay", 63.6354, 53.2198);
insert into main_station(main_station_id, name, longitude, latitude) values (5, "Pavlodar", 76.9674, 52.2873);
 --line 1 one
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (1,1, null, "08:00:00", 1, 1);
insert into station (id, main_station_id, arr_time, dep_time, day_num, schedule_id) values (2,2, "11:00:00", "11:05:00", 1, 1);
insert into station (id, main_station_id, arr_time, dep_time, day_num, schedule_id) values (3,3, "16:00:00", null, 1, 1);
--line 1 two
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (4,3, null, "17:00:00", 1, 1);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (5,2, "22:00:00", "22:05:00", 1, 1);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (6,1, "01:00:00", null, 2, 1);
--line 2 one
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (7,2, null, "12:00:00", 1, 2);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (8,4, "15:00:00", null, 1, 2);
--line 2 two
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (9,4, null, "16:00:00", 1, 2);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (10,2, "19:00:00", null, 1, 2);
 --line 3 one
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (11,1, null, "12:00:00", 1, 3);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (12,5, "15:00:00", null, 1, 3);
 --line 3 two
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (13,5, null, "19:00:00", 1, 3);
insert into station (id,main_station_id, arr_time, dep_time, day_num, schedule_id) values (14,1, "12:00:00", null, 1, 3);