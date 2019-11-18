package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="carriage", uniqueConstraints = @UniqueConstraint(columnNames = {"train_id", "carriage_id"}))
public class Carriage implements Serializable {

    private int actualSeats;
    private String type;

    @Id
    @Column(name = "carriage_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id",nullable = false)
    private Train train;

    private int number;

    @OneToMany(mappedBy = "carriage",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Seats> seat=new HashSet<>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Seats> getSeat() {
        return seat;
    }

    public void setSeat(Set<Seats> seat) {
        this.seat = seat;
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
}
