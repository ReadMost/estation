package com.javahelps.restservice.controller;
import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.repository.*;
import com.javahelps.restservice.serializer.AgentSerializer;
import com.javahelps.restservice.serializer.Temp;
import javassist.tools.web.BadHttpRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@RestController
@Service
@RequestMapping(path = "/manager")
public class ManagerController {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private MainStationRepository mainStationRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private AdjustmentRepository adjustmentRepository;
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
            Station newStation=new Station(temp.getStation().getArrTime(), temp.getStation().getDepTime(),temp.getStation().getDayNum());
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

    @PutMapping(value = "/mainStations")
    @ResponseBody
    public List<MainStation> updateMainStation(@RequestBody MainStation mainStation){
        mainStationRepository.save(mainStation);
        mainStationRepository.flush();
        System.out.println(mainStationRepository.findAll());
        return mainStationRepository.findAll();
    }
    @GetMapping(value="/agents")
    public Iterable<Agent> findAllAgents(){
        return agentRepository.findAll();
    }

    @PutMapping(value="/agents/{agent_id}")
    @ResponseBody
    Agent updateAgentHour(@PathVariable("agent_id") int agent_id, @RequestBody AgentSerializer agent){
        long hours= TimeUnit.MILLISECONDS.toHours((agentRepository.findOne(agent_id).getFrom().getTime()-agentRepository.findOne(agent_id).getTo().getTime())*-1);
        Agent a=agentRepository.findOne(agent_id);
        Adjustment adj=new Adjustment();
        adj.setT(a.getTo());
        adj.setFr(a.getFrom());
        adj.setAdjustedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        adj.setTotalHours(hours);
        adj.setAgent(a);
        agentRepository.findOne(agent_id).setAdjustment(adj);
        agentRepository.findOne(agent_id).setFrom(agent.getFrom());
        agentRepository.findOne(agent_id).setTo(agent.getTo());
        agentRepository.flush();
        adjustmentRepository.flush();
        return agentRepository.findOne(agent_id);
    }



//
//    @GetMapping(value="/agents/{agent_id}")
//        History getUpdatedHistory(@PathVariable("agent_id") int agent_id){
//        Agent a=agentRepository.findOne(agent_id);
//        Set<History> history=a.getHistory();
//       History hst=(History)history.toArray()[ history.size()-1];
//       Date lastPayed=hst.getPayed();
//       Date current=new java.sql.Date(Calendar.getInstance().getTimeInMillis());
//       List<LocalDate> list = getDatesBetweenUsingJava9(lastPayed,current);
//
//        List<Date> daysRange = Stream.iterate(lastPayed, date -> date.(1)).limit(16).collect(Collectors.toList());
//
//       Double totalSalary;
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        return null;
//    }





    @GetMapping("/stations")
    public Iterable<Station> findAllStations() {
        return stationRepository.findAll();
    }
    @GetMapping("/schedules")
    public Iterable<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/mainStations")
    public Iterable<MainStation> findAllMainStations() {
        return mainStationRepository.findAll();
    }
}
