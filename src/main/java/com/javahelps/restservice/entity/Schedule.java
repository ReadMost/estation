package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id", nullable = false)
    private Train train;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false)
    private Type type;

    @JsonManagedReference
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Station> stations=new HashSet<>(0);

    public int getId() {
        return id;
    }
    public Set<Station> getStations() {   return stations;   }
    public void setTrain(Train train) {
        this.train = train;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setStation(Set<Station> station) {
        this.stations = station;
    }

    @Transactional
    public void  deleteStation(String name){
        for(Station station:stations){
            if(station.getName().equals(name)){
               stations.remove(station);
                break;
            }
        }
    }
    @Transactional
    public void set(Station station){
        stations.add(station);
    }

}
