package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Carriage;
import com.javahelps.restservice.entity.Schedule;
import com.javahelps.restservice.entity.Station;
import com.javahelps.restservice.entity.Train;
import com.javahelps.restservice.repository.ScheduleRepository;
import com.javahelps.restservice.repository.StationRepository;
import com.javahelps.restservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/searchTrains")
public class SearchController {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TrainRepository trainRepository;

    @GetMapping("/trains")
    public Iterable<Train> findAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/stations")
    public Iterable<Station> findAllStations() {
        return stationRepository.findAll();
    }

    @GetMapping("/schedules")
    public Iterable<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}")
    public Set<Train> findByDateDepArr(@PathVariable("date") String date, @PathVariable("from") String from, @PathVariable("to") String to) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date yourDate = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(yourDate);
        int day = c.get(Calendar.DAY_OF_WEEK)%2+1;
        Set<Train> trains = trainRepository.findByDateDepArr(day, from, to);
        return trains;
    }

    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}/train/{train}")
    public Train findByDateDepArrByTrainM(@PathVariable("date") String date,
                                              @PathVariable("from") String from,
                                              @PathVariable("to") String to,
                                              @PathVariable("train") Long train) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date yourDate = sdf.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(yourDate);
        int day = c.get(Calendar.DAY_OF_WEEK)%2+1;
        Train trains = trainRepository.findByDateDepArrByTrain(day, from, to, train);

        return trains;
    }

}
