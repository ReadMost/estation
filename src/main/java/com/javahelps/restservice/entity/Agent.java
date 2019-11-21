package com.javahelps.restservice.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.transaction.Transactional;


import java.sql.Time;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Agent()
    {

    }
    public Agent(Time from, Time to){
        this.workfrom=from;
        this.workto=to;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    Date hired;

    public Date getQuit() {
        return quit;
    }

    public void setQuit(Date quit) {
        this.quit = quit;
    }

    Date quit;

    public Time getFrom() {
        return workfrom;
    }
    public void setFrom(Time from) {
        this.workfrom = from;
    }
    private Time workfrom;

    public Time getTo() {
        return workto;
    }

    public void setTo(Time to) {
        this.workto = to;
    }
    private Time workto;
    public Set<Day> getDay() {
        return day;
    }

    public void setDay(Set<Day> day) {
        this.day = day;
    }

    @ManyToMany(mappedBy = "agent",fetch = FetchType.EAGER)
    Set<Day> day=new HashSet<>();

    public double getWage() {
        return wage;
    }
    public void setWage(double wage) {
        this.wage = wage;
    }
    private double wage;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference(value="mainStation-agent")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mainStation_id", nullable = false)
    private MainStation mainStation;


    @JsonManagedReference(value="agent-history")
    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<History> history=new HashSet<>();

    public Set<Adjustment> getAdjustment() {
        return adjustment;
    }

    public void setAdjustments(Set<Adjustment> adjustment) {
        this.adjustment = adjustment;
    }

    @JsonManagedReference(value="agent-adjustment")
    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Adjustment> adjustment=new HashSet<>();


    @Transactional
    public void setAdjustment(Adjustment adjustment) {
        this.adjustment.add(adjustment);
    }
//
//
//    @Transactional
//    void setDefaultAdjustment(){
//        Adjustment adj=new Adjustment();
//        adj.setAdjustedDate(hired);
//        adj.setT(this.workto);
//        adj.setFr(this.workfrom);
//        adj.setTotalHours(TimeUnit.MILLISECONDS.toHours(-1*(this.getFrom().getTime()-this.getTo().getTime())));
//        this.adjustment.add(adj);
//    }
//


}


