package com.javahelps.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {
    @ManyToMany(mappedBy = "station")

    private Set<Schedule> schedules=new HashSet<>(0);

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String arrivalTime;
    private String departureTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
