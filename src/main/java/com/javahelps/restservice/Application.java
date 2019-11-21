package com.javahelps.restservice;

//import com.javahelps.restservice.entity.Agent;
//import com.javahelps.restservice.entity.Role;
//import com.javahelps.restservice.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//
//        User u=new User();
//        Collection<Role> roles=new ArrayList<Role>();
//        Agent agent=new Agent();
//        roles.add(agent);
//        u.setRoles(roles);

    }
}