package com.javahelps.restservice.controller;
import java.sql.Time;
import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.repository.ScheduleRepository;
import com.javahelps.restservice.repository.StationRepository;
import com.javahelps.restservice.repository.TrainRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.StreamTokenizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/manager")
public class ManagerController {

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

    @GetMapping("/trains/{id}")
    public Train getTrainById(@PathVariable("id") int id){
        return trainRepository.getTrainById(id);
    }

   @DeleteMapping("/trains/{id}/schedule/{schedule}/station/{station}")
    public void delete(@PathVariable("id") int train_id,@PathVariable("schedule") int schedule_id,@PathVariable("station") String station){
        if(trainRepository.getTrainById(train_id).getSchedules().contains(scheduleRepository.findOne(schedule_id))) {
            scheduleRepository.findOne(schedule_id).deleteStation(station);
            scheduleRepository.flush();
        }
    }



//    @PutMapping("/trains/{id}/schedule/{schedule}/station/{station}&{station_id}&{dayNum}&{deptTime}&{arrTime}")
//    public Schedule update(@PathVariable("id") int train_id, @PathVariable("schedule") int schedule_id,
//                           @RequestBody String name,@RequestBody Schedule schedule, @RequestBody int station_id, @RequestBody int dayNum,
//                           @RequestBody Time deptTime, @RequestBody Time arrTime, @RequestBody String station) throws BadHttpRequest {
//        if(trainRepository.getTrainById(train_id).getSchedules().contains(scheduleRepository.findOne(schedule_id))) {
//
//
//            Station newStation=new Station(station_id,station, arrTime, deptTime);
//            scheduleRepository.findOne(schedule_id).set(newStation);
//            scheduleRepository.flush();
//        }
//        return null;
//
//    }


    @GetMapping("/stations")
    public Iterable<Station> findAllStations() {
        return stationRepository.findAll();
    }

    @GetMapping("/schedules")
    public Iterable<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

}
