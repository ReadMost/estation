package com.javahelps.restservice.controller;

import com.javahelps.restservice.config.LogConfig;
import com.javahelps.restservice.entity.Log;
import com.javahelps.restservice.repository.LogRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(path = "/logs")
public class LogController {

    @Autowired
    private LogRepository repository;

    @GetMapping
    public Iterable<Log> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/status")
    public boolean getStatus() {
        return LogConfig.isEnabled;
    }

    @PostMapping(path = "/status/{val}")
    public void updateLog(@PathVariable("val") boolean val) {
        LogConfig.isEnabled = val;
    }
}
