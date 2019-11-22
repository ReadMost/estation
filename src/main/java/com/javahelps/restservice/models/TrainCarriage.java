package com.javahelps.restservice.models;

import java.util.List;

public class TrainCarriage {
    public int train_id;
    public int carriage_id;
    public int carriage_number;
    public String carriage_type;

    public List<Seat> seats;

    public TrainCarriage(int train_id, int carriage_id, int carriage_number, String carriage_type, List<Seat> seats) {
        this.train_id = train_id;
        this.carriage_id = carriage_id;
        this.carriage_type = carriage_type;
        this.carriage_number = carriage_number;
        this.seats = seats;
    }

}
