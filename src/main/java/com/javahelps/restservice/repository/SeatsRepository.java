package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Schedule;
import com.javahelps.restservice.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface SeatsRepository  extends JpaRepository<Seats, Integer> {
}
