package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Adjustment;
import com.javahelps.restservice.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdjustmentRepository extends JpaRepository<Adjustment, Integer> {
}
