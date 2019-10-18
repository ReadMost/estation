package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;


    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Schedule> schedules=new HashSet<>(0);


    public Set<Carriage> getCarriage() {
        return carriage;
    }

    public void setCarriage(Set<Carriage> carriage) {
        this.carriage = carriage;
    }

    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Carriage> carriage=new HashSet<>(0);


}