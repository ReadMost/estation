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

    @Column(name = "schedule_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false)
    private Type type;


  /*  @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "station_schedule",
            joinColumns = { @JoinColumn(name = "schedule_id") },
            inverseJoinColumns = { @JoinColumn(name = "station_id") }
    )*/
    @OneToMany(mappedBy = "schedule")
    private Set<Station_Schedule> station_schedules=new HashSet<>(0);

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStation(Set<Station_Schedule> station) {
        this.station_schedules = station;
    }

    public Set<Station_Schedule> getStation_schedules() {
        return station_schedules;
    }
}
