package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource(exported = false)
public interface StationRepository extends JpaRepository<Station, Integer> {

}