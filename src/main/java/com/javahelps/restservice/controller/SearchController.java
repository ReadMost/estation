package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.*;
import com.javahelps.restservice.models.Seat;
import com.javahelps.restservice.models.TrainCarriage;
import com.javahelps.restservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/searchTrains")
public class SearchController {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private CarriageRepository carriageRepository;

    @GetMapping("/trains")
    public Iterable<Train> findAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/train/{id}")
    public Train findTrainById(@PathVariable("id") int id){
        return trainRepository.getTrainById(id);
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
        java.sql.Date day = new java.sql.Date(yourDate.getTime());
        Set<Train> trains = trainRepository.findByDateDepArr(from, to);
        return trains;
    }

    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}/train/{train}/price")
    public int getPrice(@PathVariable("date") String date,
                        @PathVariable("from") String from,
                        @PathVariable("to") String to,
                        @PathVariable("train") int train) throws ParseException {
        int price = trainRepository.getPriceByTrain(1, from, to, train);
        return price;
    }

    @GetMapping("/onDate/{date}/depStation/{from}/arrStation/{to}/train/{train}")
    public Collection<TrainCarriage> findAvailableSeatsOfTrain(@PathVariable("date") String date,
                                                         @PathVariable("from") String from,
                                                         @PathVariable("to") String to,
                                                         @PathVariable("train") int train) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date yourDate = sdf.parse(date);
        java.sql.Date day = new java.sql.Date(yourDate.getTime());
        List<Object[]> seats = trainRepository.findByDateDepArrByTrain(date,from, to, train);
        Map<Integer, TrainCarriage> carriages = new HashMap<>();
        for(Object[] seat: seats) {
            int train_id = (int)seat[0];
            int carriage_id = (int)seat[1];
            int seat_id = (int)seat[2];
            Seats seatEntity = seatsRepository.findOne(seat_id);
            Carriage carriageEntity = carriageRepository.findOne(carriage_id);
            if (carriages.get(carriage_id) != null) {
                carriages.get(carriage_id).seats.add(new Seat(seatEntity.getNumber(), seatEntity.getId()));
            } else {
                carriages.put(carriage_id, new TrainCarriage(train_id, carriage_id, carriageEntity.getType(), new ArrayList<>()));
                carriages.get(carriage_id).seats.add(new Seat(seatEntity.getNumber(), seatEntity.getId()));
            }
        }

        System.out.println(" ----------- ---------");
        System.out.println(carriages);
        return carriages.values();
    }

    @SuppressWarnings("unchecked")
    public static <T extends List<?>> T castToList(Object obj) {
        return (T) obj;
    }

}
