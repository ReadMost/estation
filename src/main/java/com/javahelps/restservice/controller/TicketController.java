package com.javahelps.restservice.controller;

import com.javahelps.restservice.config.LogConfig;
import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.repository.*;

import com.javahelps.restservice.serializer.TicketSerializer;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/tickets")
public class TicketController {
    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private LogRepository logRepository;

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
    public Ticket create(@RequestBody TicketSerializer t, HttpServletRequest httpServletRequest) {
        addLog("creating new ticket with parameters: " + t,
                "POST:" + httpServletRequest.getRequestURL());
        Ticket ticket = new Ticket(t.getDocumentId(), t.getLName(), t.getFName(), carriageRepository.getOne(t.getCarriage()),
                seatsRepository.getOne(t.getSeat()), stationRepository.getOne(t.getFrom()), stationRepository.getOne(t.getTo()),
                trainRepository.getOne(t.getTrain()), userRepository.getOne(t.getUser()), t.getPrice(), t.getDate()
        );
        ticket.setStatus("booked");

        return tickerRepository.save(ticket);
    }

    @PutMapping(path = "/{id}")
    public Ticket update(@PathVariable("id") int id, @RequestBody TicketSerializer t) throws BadHttpRequest {
        if (tickerRepository.exists(id)) {
            System.out.println(t+"----------------");
            Ticket ticket = tickerRepository.getOne(id);
            ticket.setTrain(trainRepository.getOne(t.getTrain()));
            ticket.setDocument_id(t.getDocumentId());
            ticket.setlName(t.getLName());
            ticket.setfName(t.getFName());
            ticket.setCarriage(carriageRepository.getOne(t.getCarriage()));
            ticket.setSeat(seatsRepository.getOne(t.getSeat()));
            ticket.setFrom(stationRepository.getOne(t.getFrom()));
            ticket.setTo(stationRepository.getOne(t.getTo()));
            ticket.setPrice(t.getPrice());

            return tickerRepository.save(ticket);
        } else {
            throw new BadHttpRequest();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        Ticket ticket = tickerRepository.getOne(id);
        tickerRepository.delete(ticket);
    }

    @GetMapping(path = "/{id}")
    public Ticket find(@PathVariable("id") int id) {
        return tickerRepository.findOne(id);
    }

    @PutMapping(path = "cancel/{id}")
    public Ticket cancel(@PathVariable("id") int id) throws BadHttpRequest {
        if (tickerRepository.exists(id)) {
           Ticket t = tickerRepository.findOne(id);
           t.setStatus("canceled");
            return tickerRepository.save(t);
        } else {
            throw new BadHttpRequest();
        }
    }

    public void addLog(String content, String requestType) {
        if (!LogConfig.isEnabled) return;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean notAuthenticated = authentication instanceof AnonymousAuthenticationToken;
        Log log = new Log();
        if (!notAuthenticated) {
            String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
            User user = userRepository.findByEmail(username);
            log.setUser_id(user.getId());
        }
        else
            log.setUser_id(-1);
        log.setContent_type(content);
        log.setRequest_type(requestType);
        log.setDateTime(LocalDateTime.now());
        logRepository.save(log);
    }

//
//    @GetMapping("/stations")
//    public Iterable<Station> findAllStations() {
//        return stationRepository.findAll();
//
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
