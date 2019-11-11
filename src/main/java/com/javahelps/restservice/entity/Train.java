package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @Column(name="train_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;

    @JsonManagedReference
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Carriage> carriage=new HashSet<>(0);

    @JsonManagedReference
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Schedule> schedules=new HashSet<>(0);

    public String getNumber() {
        return number;
    }
    public Long getId() {
        return id;
    }
    public Set<Schedule> getSchedules() {
        return schedules;
    }
    public Set<Carriage> getCarriage() {
        return carriage;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }
    public void setCarriage(Set<Carriage> carriage) {
        this.carriage = carriage;
    }
    public void setId(Long id) {
        this.id = id;
    }

}