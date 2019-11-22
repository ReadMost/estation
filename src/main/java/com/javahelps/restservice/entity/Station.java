package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "station")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Station {

    @Id
    @Column(name="station_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private Time arrTime;
    private Time depTime;
    private int dayNum;


    public Station(){

    }
    public Station(Time arrTime, Time depTime, int dayNum){
        this.arrTime=arrTime;
        this.depTime=depTime;
        this.dayNum=dayNum;
    }

    @JsonBackReference(value="schedule-station")
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
    public void setDepTime(Time depTime) { this.depTime = depTime;   }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setTicketFroms(Set<Ticket> ticketFroms) {
        this.ticketFroms = ticketFroms;
    }

    public void setTicketTos(Set<Ticket> ticketTos) {
        this.ticketTos = ticketTos;
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

//    public Set<Ticket> getTicket_from() {
//        return ticketFroms;
//    }

    public void setTicket_from(Set<Ticket> ticket_from) {
        this.ticketFroms = ticket_from;
    }

//    public Set<Ticket> getTicket_to() {
//        return ticketTos;
//    }

    public void setTicket_to(Set<Ticket> ticket_to) {
        this.ticketTos = ticket_to;
    }

    @JsonManagedReference(value="from-get")
    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL
            ,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticketFroms=new HashSet<>(0);

    @JsonManagedReference(value="to-get")
    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticketTos=new HashSet<>(0);

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

//    @OneToOne(mappedBy = "station")
//    private Manager manager;
    public void setMainStation(MainStation mainStation) {
        this.mainStation = mainStation;
    }

    @JsonBackReference(value="mainStation-station")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="mainStation_id")
    private MainStation mainStation;

    @Transactional
    public MainStation getMainStation(){
        return mainStation;
    }

    @Transactional
    public String getMainStationName(){
        return mainStation.getName();
    }
    @Transactional
    public Integer getMainStationID(){
        return mainStation.getId();
    }



}
