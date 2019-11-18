package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
//@JsonIgnoreProperties(ignoreUnknown = true,
//        value = {"hibernateLazyInitializer", "handler", "created"})
@JsonIgnoreProperties("user")
public class Ticket implements java.io.Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Seats getSeat() {
        return seat;
    }

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int price;

    private int document_id;

    private String lName;

    private String fName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carriage_id")
    @JsonBackReference(value="carriage-get")
    private Carriage carriage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    @JsonBackReference(value="user-get")
    private User user;

    public Ticket(int document_id, String lName, String fName, Carriage carriage, Seats seat, Station from, Station to, Train train, User user, Integer price) {
        this.document_id = document_id;
        this.lName = lName;
        this.fName = fName;
        this.carriage = carriage;
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.train = train;
        this.user = user;
        this.price = price;

    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seats_id")
    @JsonBackReference(value="seat-get")
    private Seats seat;


    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="from_station_id")
    @JsonBackReference(value="from-get")
    private Station from;

    @ManyToOne
    @JoinColumn(name="to_station_id")
    @JsonBackReference(value="to-get")
    private Station to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id")
    @JsonBackReference(value="train-get")
    private Train train;

    private String status;

    public Carriage getCarriage() {
        return carriage;
    }

    public User getUser() {
        return user;
    }

    public Train getTrain() {
        return train;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public Ticket() {
    }

    public void setfName(String fName) {
        this.fName = fName;
    }



    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }



    public void setSeat(Seats seat) {
        this.seat = seat;
    }



    public void setFrom(Station from) {
        this.from = from;
    }



    public void setTo(Station to) {
        this.to = to;
    }



    public void setTrain(Train train) {
        this.train = train;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
