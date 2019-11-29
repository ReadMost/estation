package com.javahelps.restservice.repository;


import com.javahelps.restservice.entity.Log;
import com.javahelps.restservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface LogRepository extends JpaRepository<Log, Integer> {
}