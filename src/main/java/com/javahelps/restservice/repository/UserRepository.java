package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.javahelps.restservice.entity.User;

import javax.transaction.Transactional;
import java.util.Set;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User SET first_name = :firstName, last_name = :lastName WHERE id = :id", nativeQuery = true)
    void updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User SET password = :password  WHERE id = :id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("id") Long id);

}