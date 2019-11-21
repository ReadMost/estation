package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Seats;
import com.javahelps.restservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Date;
import java.util.List;
import java.util.Set;


@RestResource(exported = false)
public interface TrainRepository extends JpaRepository<Train, Integer> {
    @Query(value = "select * from train t, schedule s where t.train_id=s.train_id and s.type_id=:d order by t.train_id desc", nativeQuery = true)
    Set<Train> findbyScheduleType(@Param("d") int d);

    @Query(value = "select count(station.name)\n" +
            "FROM carriage as c, Train as T, Schedule as S, Station as ST1,  Station as ST2, station as station\n" +
            "where t.train_id=c.train_id and t.train_id = s.train_id and St1.schedule_id = s.id  and  St2.schedule_id = s.id\n" +
            "  and s.type_id=:d and ST1.name =:dep and ST2.name =:arr and c.train_id=:train and station.schedule_id=s.id\n" +
            "  and TIME(ST1.dep_time) < TIME(ST2.dep_time) and TIME(ST1.day_num) <= TIME(ST2.day_num) and station.arr_time>=ST1.dep_time and station.arr_time<=ST2.arr_time\n ", nativeQuery = true)
    int getPriceByTrain(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr, @Param("train") int train);

    @Query(value = "select * FROM Train t, Schedule s, Station st1,  Station st2, main_station m1, main_station m2\n" +
            "            where s.type_id=1 and t.train_id = s.train_id and St1.schedule_id = s.id\n" +
            "            and St2.schedule_id = s.id and m1.main_station_id=st1.main_station_id " +
            "            and m2.main_station_id=st2.main_station_id\n" +
            "            and m1.name =:dep and m2.name =:arr\n" +
            "            and TIME(ST1.dep_time) < TIME(ST2.arr_time)\n" +
            "            and ST1.day_num <= ST2.day_num", nativeQuery = true)
    Set<Train> findByDateDepArr( @Param("dep") String dep, @Param("arr") String arr);

    @Query(value = "select  t.train_id, c.carriage_id, s.seat_id FROM Train t, Schedule sc, Station st1,  Station st2, main_station m1, main_station m2, carriage c, seats s  left outer join (\n" +
            "    select distinct seats_id\n" +
            "    from ticket as TC, Train t, Schedule sc, Station st1,  Station st2, main_station m1, main_station m2, carriage c, seats s\n" +
            "    where t.train_id=:train and sc.type_id=1 and t.train_id = sc.train_id and St1.schedule_id = sc.id\n" +
            "      and  St2.schedule_id = sc.id and m1.main_station_id=st1.main_station_id\n" +
            "      and m2.main_station_id=st2.main_station_id\n" +
            "      and t.train_id = c.train_id and c.carriage_id=s.carriage_id\n" +
            "      and m1.name =:dep and m2.name =:arr\n" +
            "      and TIME(ST1.dep_time) < TIME(ST2.arr_time)\n" +
            "      and ST1.day_num <= ST2.day_num and TC.seats_id=s.seat_id and TC.date =:date and TC.status like 'booked' and TC.train_id = t.train_id and TC.carriage_id = c.carriage_id) as Booked on Booked.seats_id = s.seat_id\n" +
            "    where sc.type_id=1 and t.train_id = sc.train_id and St1.schedule_id = sc.id\n" +
            "      and  St2.schedule_id = sc.id and m1.main_station_id=st1.main_station_id\n" +
            "      and m2.main_station_id=st2.main_station_id\n" +
            "      and t.train_id = c.train_id and c.carriage_id=s.carriage_id\n" +
            "      and m1.name =:dep and m2.name =:arr\n" +
            "      and TIME(ST1.dep_time) < TIME(ST2.arr_time)\n" +
            "      and ST1.day_num <= ST2.day_num\n" +
            "      and Booked.seats_id is NULL\n" +
            "    group by s.seat_id", nativeQuery = true)
    List<Object[]> findByDateDepArrByTrain(@Param("date") String date,
                                        @Param("dep") String dep, @Param("arr") String arr, @Param("train") int train);

    Train getTrainById(@Param("id") int id);
}