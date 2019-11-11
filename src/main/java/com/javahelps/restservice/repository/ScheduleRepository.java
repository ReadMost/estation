package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Schedule;
import com.javahelps.restservice.entity.Station;
import com.javahelps.restservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;


@RestResource(exported = false)
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Set<Schedule> findScheduleByType_Id(@Param("t") int t);
}