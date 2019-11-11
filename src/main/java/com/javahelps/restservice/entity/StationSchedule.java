/*package com.javahelps.restservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "station_schedule")
public class StationSchedule {
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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setStation(Station station) {
        this.station = station;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

} */