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
public interface TrainRepository extends JpaRepository<Train, Long> {
    @Query(value = "select count(station.name)\n" +
            "FROM carriage as c, Train as T, Schedule as S, Station as ST1,  Station as ST2, station as station\n" +
            "where t.train_id=c.train_id and t.train_id = s.train_id and St1.schedule_id = s.id  and  St2.schedule_id = s.id\n" +
            "  and s.type_id=:d and ST1.name =:dep and ST2.name =:arr and c.train_id=:train and station.schedule_id=s.id\n" +
            "  and TIME(ST1.dep_time) < TIME(ST2.dep_time) and TIME(ST1.day_num) <= TIME(ST2.day_num) and station.arr_time>=ST1.dep_time and station.arr_time<=ST2.arr_time;\n ", nativeQuery = true)
    int getPriceByTrain(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr, @Param("train") Long train);

    @Query(value = "select * FROM Train t, Schedule s, Station st1,  Station st2 where s.type_id=:d and t.train_id = s.train_id and St1.schedule_id = s.id  and  St2.schedule_id = s.id and ST1.name =:dep and ST2.name =:arr and TIME(ST1.dep_time) < TIME(ST2.dep_time) and ST1.day_num <= ST2.day_num GROUP BY t.train_id", nativeQuery = true)
    Set<Train> findByDateDepArr(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr);

    @Query(value = "select * FROM carriage as c, Train t, Schedule s, Station st1,  Station st2 where t.train_id=c.train_id and c.train_id=:train and s.type_id=:d and t.train_id = s.train_id and St1.schedule_id = s.id  and  St2.schedule_id = s.id and ST1.name =:dep and ST2.name =:arr and TIME(ST1.dep_time) < TIME(ST2.dep_time) and ST1.day_num <= ST2.day_num GROUP BY t.train_id", nativeQuery = true)
    Train findByDateDepArrByTrain(@Param("d") int d, @Param("dep") String dep, @Param("arr") String arr, @Param("train") Long train);

}