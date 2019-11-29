package com.javahelps.restservice.controller;


import com.javahelps.restservice.config.LogConfig;
import com.javahelps.restservice.entity.Log;
import com.javahelps.restservice.entity.Role;
import com.javahelps.restservice.repository.AgentRepository;
import com.javahelps.restservice.repository.LogRepository;
import com.javahelps.restservice.repository.RoleRepository;
import com.javahelps.restservice.serializer.UserSerializer;

import com.javahelps.restservice.serializer.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.javahelps.restservice.entity.User;
import com.javahelps.restservice.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Collection;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AgentRepository agentRepository;

    @GetMapping
    public Iterable<User> findAll(HttpServletRequest httpServletRequest) {
        addLog("get all users' list", "GET:"+ httpServletRequest.getRequestURL());
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User find(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @GetMapping(path = "/user_info")
    public User currentUserName(Principal principal) {
        return repository.findByEmail(principal.getName());
    }


    @PostMapping(consumes = "application/json")
    public User create(@RequestBody UserSerializer user, HttpServletRequest httpServletRequest) {

        System.out.println(user.getAgent());
        Role r = createRoleIfNotFound("ROLE_PASSENGER");
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRoles(r);
        addLog("creating new user with parameters: " + user, "POST:" + httpServletRequest.getRequestURL());
        User u = repository.save(user.createUser());
        u.setAgent(agentRepository.save(user.getAgent()));
        return repository.save(u);
    }



    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        addLog("deleting  user with id: " + id, "DELETE:" + httpServletRequest.getRequestURL());
        repository.delete(id);
    }

    private void updateUser(User oldUser, User newUser){
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
    }

    @PutMapping(path = "/{id}")
    public User update_user(@PathVariable("id") Long id, @RequestBody UserUpdate user, HttpServletRequest httpServletRequest) throws BadHttpRequest {
        if (repository.exists(id)) {
            System.out.println(user.getFirstName());
            User u = repository.getOne(id);
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            addLog("updating user with id: " + id +" with parameters: " + user + "SUCCESS",
                    "PUT:" + httpServletRequest.getRequestURL());
            return repository.save(u);
        } else {
            addLog("updating user with id: " + id +" with parameters: " + user + "FAILURE",
                    "PUT:" + httpServletRequest.getRequestURL());
            throw new BadHttpRequest();
        }
    }

    @PutMapping(path = "/password/{id}")
    public User updatePassword(@PathVariable("id") Long id, @RequestBody String password) throws BadHttpRequest {
        if (repository.exists(id)) {

            repository.updatePassword(new BCryptPasswordEncoder().encode(password), id);
            User u = repository.findById(id);
            return u;
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


    public void addLog(String content, String requestType) {
        if (!LogConfig.isEnabled) return;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean notAuthenticated = authentication instanceof AnonymousAuthenticationToken;
        Log log = new Log();
        if (!notAuthenticated) {
            String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
            User user = repository.findByEmail(username);
            log.setUser_id(user.getId());
        }
        else
            log.setUser_id(-1);
        log.setContent_type(content);
        log.setRequest_type(requestType);
        log.setDateTime(LocalDateTime.now());
        logRepository.save(log);
    }

}
