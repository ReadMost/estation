package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Manager;
import com.javahelps.restservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
