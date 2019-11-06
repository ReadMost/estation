package com.javahelps.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {
    @OneToMany(mappedBy = "station")
    private Set<Station_Schedule> station_schedules=new HashSet<>(0);

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "station_id")
    private Long id;

    @Column
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

    public Set<Station_Schedule> getSchedules() {
        return station_schedules;
    }

    public void setSchedules(Set<Station_Schedule> schedules) {
        this.station_schedules = schedules;
    }
}
