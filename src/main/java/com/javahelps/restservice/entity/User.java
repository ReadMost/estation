package com.javahelps.restservice.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {



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

    public User(String lName, String fName) {
        setlName(lName);
        setfName(fName);
    }

    @OneToMany(mappedBy="user")
    @Value("${some.key:}")
    private Set<Passenger> passengers;

}