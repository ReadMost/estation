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

    public Passenger(String email, String password, String phone, User user) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.user = user;
    }

    public Passenger() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.addPassengers(this);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(unique = true)
    private String email;
    private String password;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;
}
