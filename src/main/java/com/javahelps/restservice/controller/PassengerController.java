package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Passenger;
import com.javahelps.restservice.repository.PassengerRepository;
import com.javahelps.restservice.repository.UserRepository;
import com.javahelps.restservice.serializer.PassengerSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import com.javahelps.restservice.entity.User;


import javassist.tools.web.BadHttpRequest;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/passengers")

public class PassengerController {

    @Autowired
    private PassengerRepository repository;

    @Autowired
    private UserRepository repository_user;

    @GetMapping
    public Iterable<Passenger> findAll() {
        return repository.findAll();
    }
//    @GetMapping("/check/{email}")
//    public String check(@PathVariable("email") String email) {
//
//        Passenger credentials = PassengerRepository.findByEmail(email);
//
//        return credentials;
//    }
    @GetMapping(path = "/{id}")
    public Passenger find(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = "application/json")
    public Passenger create(@RequestBody PassengerSerializer passenger) {
        User u = repository_user.save(new User(passenger.getUser().getLName(),passenger.getUser().getFName()));
        Passenger p = repository.save(new Passenger(passenger.getEmail(), passenger.getPassword(), passenger.getPhone(), u));
        return p;


    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

    @PutMapping(path = "/{id}")
    public Passenger update(@PathVariable("id") Long id, @RequestBody Passenger passenger) throws BadHttpRequest {
        if (repository.exists(id)) {
            return repository.save(passenger);
        } else {
            throw new BadHttpRequest();
        }
    }

}
