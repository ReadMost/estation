package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.MainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource(exported = false)
public interface MainStationRepository  extends JpaRepository<MainStation, Integer> {

    MainStation getMainStationByName(@Param("name") String name);



}

