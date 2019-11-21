package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.History;
import com.javahelps.restservice.entity.MainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    History findByAgent_Id(@Param("id")int id);
}
