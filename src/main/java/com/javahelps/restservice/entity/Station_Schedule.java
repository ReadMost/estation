package com.javahelps.restservice.entity;

import javax.persistence.*;


@Entity
@Table(name = "station_schedule")
public class Station_Schedule {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private Station station;

    @JoinColumn(name = "schedule_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    //additional
    @Column(name = "arr_time")
    private String arr_time;
    @Column(name = "dep_time")
    private String dep_time;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Schedule getSchedules() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }



}
