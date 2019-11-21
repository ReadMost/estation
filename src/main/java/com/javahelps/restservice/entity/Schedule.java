package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name="schedule")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonBackReference(value="train-schedule")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id", nullable = false)
    private Train train;

    @JsonBackReference(value="type-schedule")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", nullable = false)
    private Type type;




    @JsonManagedReference(value="schedule-station")
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.MERGE,
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
    public void deleteStation(String name){
        for(Station station:stations){
         if(station.getMainStation().getName().equals(name)){
             System.out.println(station.getMainStation().getName());
             stations.remove(station);

             break;
         }
        }
    }


    @Transactional
    public void set(Station station){
        this.stations.add(station);
    }

    @Transactional
    public Station getStationByName(String name){
        for(Station station:stations){
            if(station.getMainStation().getName().equals(name)){
                System.out.println(station);
                return station;
            }
        }
        return null;
    }

}
