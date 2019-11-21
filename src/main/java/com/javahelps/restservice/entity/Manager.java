package com.javahelps.restservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;
import javax.persistence.*;

@Entity
public class Manager {
    @Id
    @GeneratedValue
    private Long id;

    public Manager() {
    }

    public User getUser() {
        return user;
    }
    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "station_id")
//    private Station station;

}
