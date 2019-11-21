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

    @Column(unique = true)
    private String phone;

    public Manager(String phone, User user) {

        this.phone = phone;
        this.user = user;
    }

    public Manager() {
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private Station station;
}
