package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Passenger;
import com.javahelps.restservice.repository.PassengerRepository;
import com.javahelps.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import com.javahelps.restservice.entity.User;


import javassist.tools.web.BadHttpRequest;

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

    @GetMapping(path = "/{id}")
    public Passenger find(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = "application/json")
    public Passenger create(@RequestBody Passenger passenger) {
        System.out.println( passenger.fName + "         ------------------------------");
        User u = repository_user.save(new User(passenger.lName, passenger.fName));
        passenger.setUser(u);
        return repository.save(passenger);


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
