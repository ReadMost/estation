package com.javahelps.restservice.repository;


import com.javahelps.restservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource(exported = false)
public interface TrainRepository extends JpaRepository<Train, Long> {

}