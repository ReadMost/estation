package com.javahelps.restservice;

import com.javahelps.restservice.entity.Role;
import com.javahelps.restservice.entity.User;
import com.javahelps.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
// uncomment if you want to use Spring XML Configuration
// @ImportResource("classpath:spring-security-config.xml")
public class Run {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User(
                "Memory",
                "Not Found",
                "rauan.ru@gmail.com",
                passwordEncoder.encode("qwerty"),
                Arrays.asList(
                        new Role("ROLE_PASSENGER"),
                        new Role("ROLE_MANAGER"),
                        new Role("ROLE_EMPLOYEE")));

        if (userRepository.findByEmail(user.getEmail()) == null){
            userRepository.save(user);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}