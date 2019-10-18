package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {



    @Id
    @GeneratedValue
    private Long id;


    public String getlName() {
        return lName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                '}';
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    private String lName;
    private String fName;

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassengers(Passenger passengers) {
        this.passengers.add(passengers);
    }

    public User() {
    }

    public User(String lName, String fName) {
        setlName(lName);
        setfName(fName);
    }

    @JsonBackReference
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @Value("${some.key:}")
    private Set<Passenger> passengers;

}