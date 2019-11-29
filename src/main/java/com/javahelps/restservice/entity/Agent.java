package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    int salary;
    Date workedSince;
    Date workedTill;
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

    public Agent(int salary, Time workedSince, Time workedTill, Time workDailyFrom, Time workDailyTill, User user) {
        this.salary = salary;
        this.workedSince = workedSince;
        this.workedTill = workedTill;
        this.workDailyFrom = workDailyFrom;
        this.workDailyTill = workDailyTill;
        this.user = user;
    }

    public Agent() {
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getWorkedSince() {
        return workedSince;
    }

    public void setWorkedSince(Date workedSince) {
        this.workedSince = workedSince;
    }

    public Date getWorkedTill() {
        return workedTill;
    }

    public void setWorkedTill(Date workedTill) {
        this.workedTill = workedTill;
    }

    public int getId() {  return id; }

    public void setId(int id) { this.id = id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "user-agent")
    private User user;



}
