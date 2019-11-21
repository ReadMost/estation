package com.javahelps.restservice.repository;


import com.javahelps.restservice.entity.Carriage;
import com.javahelps.restservice.entity.Schedule;
import com.javahelps.restservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Time;
import java.util.List;
import java.util.Set;


@RestResource(exported = false)
public interface TrainRepository extends JpaRepository<Train, Integer> {
    @Query(value = "select * from train t, schedule s where t.train_id=s.train_id and s.type_id=:d order by t.train_id desc", nativeQuery = true)
    Set<Train> findbyScheduleType(@Param("d") int d);

    @Query(value = "select * FROM Train t, Schedule s, Station st1,  Station st2, main_station m1, main_station m2\n" +
            "where s.type_id=:d and t.train_id = s.train_id and St1.schedule_id = s.id\n" +
            "  and  St2.schedule_id = s.id and m1.main_station_id=st1.main_station_id and m2.main_station_id=st2.main_station_id\n" +
            "  and m1.name =:dep and m2.name =:arr\n" +
            "  and TIME(ST1.dep_time) < TIME(ST2.arr_time)\n" +
            "  and ST1.day_num <= ST2.day_num", nativeQuery = true)
    Set<Train> findByDateDepArr(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr);

    @Query(value = "select * FROM carriage as c, Train t, Schedule s, Station st1,  Station st2, main_station m1, main_station m2 \n" +
            "where t.train_id=c.train_id and c.train_id=:train and s.type_id=:d and t.train_id = s.train_id and m1.main_station_id=st1.main_station_id and m2.main_station_id=st2.main_station_id\n" +
            "  and St1.schedule_id = s.id  and  St2.schedule_id = s.id and m1.name =:dep and m2.name =:arr \n" +
            "and TIME(ST1.dep_time) < TIME(ST2.arr_time) and ST1.day_num <= ST2.day_num", nativeQuery = true)
    Train findByDateDepArrByTrain(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr, @Param("train") Long train);

    Train getTrainById(@Param("id") int id);
}