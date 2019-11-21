package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    int salary;
    Time workedSince;
    Time workedTill;
    Time workDailyFrom;
    Time workDailyTill;

    public Time getWorkDailyFrom() {
        return workDailyFrom;
    }

    public void setWorkDailyFrom(Time workDailyFrom) {
        this.workDailyFrom = workDailyFrom;
    }

    public Time getWorkDailyTill() {
        return workDailyTill;
    }

    public void setWorkDailyTill(Time workDailyTill) {
        this.workDailyTill = workDailyTill;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Time getWorkedSince() {
        return workedSince;
    }

    public void setWorkedSince(Time workedSince) {
        this.workedSince = workedSince;
    }

    public Time getWorkedTill() {
        return workedTill;
    }

    public void setWorkedTill(Time workedTill) {
        this.workedTill = workedTill;
    }

    public int getId() {  return id; }

    public void setId(int id) { this.id = id; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;



}
