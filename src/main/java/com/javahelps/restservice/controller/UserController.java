package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Role;
import com.javahelps.restservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javahelps.restservice.entity.User;
import com.javahelps.restservice.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;



    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User find(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user) {
        Role r = createRoleIfNotFound("ROLE_PASSENGER");
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRoles(r);
        return repository.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

    @PutMapping(path = "/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) throws BadHttpRequest {
        if (repository.exists(id)) {
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }
    @Transactional
    User createUserIfNotFound(String fname, String lname, String password, String email) {

        User user = repository.findByEmail(email);
        if (user == null) {
            Collection<Role> roles = new  ArrayList<Role>();
            user = new User(fname, lname, email, password, roles);
            repository.save(user);
        }
        return user;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

}
