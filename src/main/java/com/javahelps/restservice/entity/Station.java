package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "station_id")
    private Long id;
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
    public Long getId() {
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setSchedules(Schedule schedule) {
        this.schedule = schedule;
    }
}
