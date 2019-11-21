package com.javahelps.restservice.models;

import java.util.List;

public class TrainCarriage {
    public int train_id;
    public int carriage_id;
    public String carriage_type;

    public List<Seat> seats;

    public TrainCarriage(int train_id, int carriage_id, String carriage_type, List<Seat> seats) {
        this.train_id = train_id;
        this.carriage_id = carriage_id;
        this.carriage_type = carriage_type;
        this.seats = seats;
    }

    public TrainCarriage() {
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getCarriage_id() {
        return carriage_id;
    }

    public void setCarriage_id(int carriage_id) {
        this.carriage_id = carriage_id;
    }

    public String getCarriage_type() {
        return carriage_type;
    }

    public void setCarriage_type(String carriage_type) {
        this.carriage_type = carriage_type;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
