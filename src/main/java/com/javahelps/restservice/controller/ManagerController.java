package com.javahelps.restservice.controller;
import java.sql.Time;
import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.repository.*;
import com.javahelps.restservice.serializer.Temp;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    private MainStationRepository mainStationRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private AgentRepository agentRepository;

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

        if(trainRepository.getTrainById(train_id).getSchedules().contains(scheduleRepository.findOne(schedule_id))){
            Station st= scheduleRepository.findOne(schedule_id).getStationByName(station);
            mainStationRepository.getMainStationByName(station).delStation(st);
            mainStationRepository.flush();
            scheduleRepository.findOne(schedule_id).deleteStation(station);
            scheduleRepository.flush();
        }
    }

    @PutMapping(value = "/trains/{id}/schedule/{schedule}/")
    @ResponseBody
    public Schedule updateRoute(@PathVariable("id") int train_id, @PathVariable("schedule") int schedule_id,
                                @RequestBody Temp temp) throws BadHttpRequest, ParseException {
        if(trainRepository.getTrainById(train_id).getSchedules().contains(scheduleRepository.findOne(schedule_id))) {
            MainStation ms=new MainStation();
            List<MainStation> mainStations=mainStationRepository.findAll();
            for(MainStation main:mainStations){
                if(main.getName().equals(temp.getName())){
                    ms=main;
                }
            }
            Station newStation=new Station(temp.getArrTime(), temp.getDepTime(),temp.getDayNum());
            Schedule schedule = scheduleRepository.findOne(schedule_id);
            newStation.setSchedules(schedule);
            newStation.setMainStation(mainStationRepository.save(ms));
            stationRepository.save(newStation);
            schedule.set(newStation);
            scheduleRepository.save(schedule);
            stationRepository.save(newStation);
            ms.setStation(newStation);
            mainStationRepository.save(ms);
            scheduleRepository.flush();
            stationRepository.flush();
            mainStationRepository.flush();
        }
        return scheduleRepository.findOne(schedule_id);
    }

    @GetMapping(value="/agents")
    public Iterable<Agent> findAllAgents(){
        return agentRepository.findAll();
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
