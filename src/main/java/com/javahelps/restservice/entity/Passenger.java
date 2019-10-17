package com.javahelps.restservice.entity;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String phone;



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

    private String email;
    private String password;
    public String fName;
    public String lName;
    @ManyToOne
    @JoinColumn(name="user_fk")
    private User user;
}
