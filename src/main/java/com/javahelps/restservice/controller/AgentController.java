package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Ticket;
import com.javahelps.restservice.repository.*;
import com.javahelps.restservice.serializer.TicketSerializer;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/agentRequests")
public class AgentController {
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
}
