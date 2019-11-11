package com.javahelps.restservice.entity;

import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "type",fetch = FetchType.EAGER)
    private Set<Day> day=new HashSet<>();

    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Schedule> schedules=new HashSet<>();

    public int getId() {
        return id;
    }
    public Set<Day> getDay() {
        return day;
    }
    public Set<Schedule> getSchedules() {  return schedules;  }

    public void setId(int id) {
        this.id = id;
    }
    public void setDay(Set<Day> day) {
        this.day = day;
    }
    public void setSchedules(Set<Schedule> schedules) { this.schedules = schedules;  }

}
