package com.javahelps.restservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;
import javax.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String phone;

    public Passenger(String phone, User user) {

        this.phone = phone;
        this.user = user;
    }

    public Passenger() {
    }

    public User getUser() {
        return user;
    }

//    public void setUser(User user) {
//        this.user = user;
//        this.user.addPassengers(this);
//    }

    @Override
    public String toString() {
        return "Passenger{" +
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
}
