package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.repository.*;

import com.javahelps.restservice.serializer.TicketSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tickets")
public class TicketController {
    @Autowired
    private TickerRepository tickerRepository;
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarriageRepository carriageRepository;

    @Autowired
    private SeatsRepository seatsRepository;

    @GetMapping
    public List<Ticket> findAll() {
        return tickerRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public Ticket create(@RequestBody TicketSerializer t) {
        Ticket ticket = new Ticket(t.getDocumentId(), t.getLName(), t.getFName(), carriageRepository.getOne(t.getCarriage()),
                seatsRepository.getOne(t.getSeat()), stationRepository.getOne(t.getFrom()), stationRepository.getOne(t.getTo()),
                trainRepository.getOne(t.getTrain()), userRepository.getOne(t.getUser()), t.getPrice()
        );

        return tickerRepository.save(ticket);
    }
//
//    @GetMapping("/stations")
//    public Iterable<Station> findAllStations() {
//        return stationRepository.findAll();
//    }
//
//    @GetMapping("/schedules")
//    public Iterable<Schedule> findAllSchedules() {
//        return scheduleRepository.findAll();
//    }
//
//    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}")
//    public Set<Train> findByDateDepArr(@PathVariable("date") String date, @PathVariable("from") String from, @PathVariable("to") String to) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date yourDate = sdf.parse(date);
//        Calendar c = Calendar.getInstance();
//        c.setTime(yourDate);
//        int day = c.get(Calendar.DAY_OF_WEEK)%2+1;
//        Set<Train> trains = trainRepository.findByDateDepArr(day, from, to);
//        return trains;
//    }
//
//    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}/train/{train}")
//    public Train findByDateDepArrByTrainM(@PathVariable("date") String date,
//                                          @PathVariable("from") String from,
//                                          @PathVariable("to") String to,
//                                          @PathVariable("train") Long train) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date yourDate = sdf.parse(date);
//        Calendar c = Calendar.getInstance();
//        c.setTime(yourDate);
//        int day = c.get(Calendar.DAY_OF_WEEK)%2+1;
//        Train trains = trainRepository.findByDateDepArrByTrain(day, from, to, train);
//
//        return trains;
//    }
}
