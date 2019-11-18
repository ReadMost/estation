package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "station")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Time arrTime;
    private Time depTime;
    private int dayNum;

    @JsonBackReference
    @ManyToOne
    private Schedule schedule;

    public String getName() {
        return name;
    }
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
    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSchedules(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<Ticket> getTicket_from() {
        return ticketFroms;
    }

    public void setTicket_from(Set<Ticket> ticket_from) {
        this.ticketFroms = ticket_from;
    }

    public Set<Ticket> getTicket_to() {
        return ticketTos;
    }

    public void setTicket_to(Set<Ticket> ticket_to) {
        this.ticketTos = ticket_to;
    }

    @JsonManagedReference(value="from-get")
    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticketFroms=new HashSet<>(0);

    @JsonManagedReference(value="to-get")
    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticketTos=new HashSet<>(0);
}
