package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mainStation")
public class MainStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mainStation_id")
    private int mainStation_id;
    private String name;
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

    @Transactional
    public void setStation(Station station){
        stations.add(station);
    }
    @Transactional
    public void delStation(Station station){
        stations.remove(station);
    }

    @Override
    public String toString() {
        return "MainStation{" +
                "mainStation_id=" + mainStation_id +
                ", name='" + name + '\'' +
                ", stations=" + stations +
                '}';
    }

    @JsonManagedReference(value="mainStation-agent")
    @OneToMany(mappedBy = "mainStation",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Agent> agents= new HashSet<>();


}
