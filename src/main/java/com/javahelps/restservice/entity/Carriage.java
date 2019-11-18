package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="carriage", uniqueConstraints = @UniqueConstraint(columnNames = {"train_id", "carriage_id"}))
public class Carriage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="carriage_id")
    private int id;
    private int actualSeats;
    private String type;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id",nullable = false)
    private Train train;


    private int number;


    @OneToMany(mappedBy = "carriage",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Seats> seats=new HashSet<>(0);

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Seats> getSeat() {
        return seats;
    }

    public void setSeat(Set<Seats> seat) {
        this.seats = seat;
    }

    public int getActualSeats() {
        return actualSeats;
    }

    public void setActualSeats(int actualSeats) {
        this.actualSeats = actualSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }



    public Set<Seats> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seats> seats) {
        this.seats = seats;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "carriage",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticket=new HashSet<>(0);
}
