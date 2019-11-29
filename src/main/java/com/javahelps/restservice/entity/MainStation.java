package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mainStation")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="non-sense")
public class MainStation {
    @Id
    @Column(name = "main_station_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mainStation_id;

    private String name;

    private Long longitude;

    private Long latitude;

    public int getId() {
        return mainStation_id;
    }

    public void setId(int id) {
        this.mainStation_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    @JsonManagedReference(value="mainStation-station")
    @OneToMany(mappedBy = "mainStation", cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Station> stations=new HashSet<>(0);

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    @Transactional
    public void setStation(Station station){
        stations.add(station);
    }
    @Transactional
    public void delStation(Station station){
        stations.remove(station);
    }
}