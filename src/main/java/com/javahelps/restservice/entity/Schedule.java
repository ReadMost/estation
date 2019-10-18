package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name="schedule")
public class Schedule {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id", nullable = false)
    private Train train;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false)
    private Type type;


    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "station_schedule",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "station_id") }
    )
   private Set<Station> station=new HashSet<>(0);


    public void setTrain(Train train) {
        this.train = train;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Station> getStation() {
        return station;
    }

    public void setStation(Set<Station> station) {
        this.station = station;
    }
}
