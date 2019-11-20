package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Carriage;
import com.javahelps.restservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface CarriageRepository extends JpaRepository<Carriage, Integer> {
    Carriage findById(Long id);
}