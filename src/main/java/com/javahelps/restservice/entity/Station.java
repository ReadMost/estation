package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "station")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="non-sense")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Time arrTime;
    private Time depTime;
    private int dayNum;


    public Station() {    }

    public Station(int id, Time arrTime, Time depTime, int dayNum) {
        this.id = id;
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.dayNum = dayNum;
    }

    public Station(Time arrTime, Time depTime, int dayNum){
        this.arrTime=arrTime;
        this.depTime=depTime;
        this.dayNum=dayNum;
    }

    @JsonBackReference(value = "schedule-station")
    @ManyToOne
    private Schedule schedule;


    public int getId() {
        return id;
    }

    public int getDayNum() {
        return dayNum;
    }

    public Time getDepTime() {
        return depTime;
    }

    public Time getArrTime() {
        return arrTime;
    }

    public void setArrTime(Time arrTime) {
        this.arrTime = arrTime;
    }

    public void setDepTime(Time depTime) {
        this.depTime = depTime;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchedules(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setTicket_from(Set<Ticket> ticket_from) {
        this.ticketFroms = ticket_from;
    }

    public void setTicket_to(Set<Ticket> ticket_to) {
        this.ticketTos = ticket_to;
    }

    @JsonManagedReference(value = "from-get")
    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> ticketFroms = new HashSet<>(0);

    @JsonManagedReference(value = "to-get")
    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> ticketTos = new HashSet<>(0);

    @JsonBackReference(value = "mainStation-station")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "main_station_id", nullable = false)
    private MainStation mainStation;

    @JsonIgnore
    @Transactional
    public MainStation getMainStation() {
        return mainStation;
    }

    @OneToOne(mappedBy = "station")
    private Manager manager;

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", arrTime=" + arrTime +
                ", depTime=" + depTime +
                ", dayNum=" + dayNum +
                ", schedule=" + schedule +
                ", ticketFroms=" + ticketFroms +
                ", ticketTos=" + ticketTos +
                '}';
    }

    public void setMainStation(MainStation mainStation) {
        this.mainStation = mainStation;
    }

    public String getMainStation_name(){
        return mainStation.getName();
    }

    public Long getLongitude(){
        return mainStation.getLongitude();
    }

    public Long getLatitude(){
        return mainStation.getLatitude();
    }

    public int getMainStation_id(){
        return mainStation.getId();
    }
}