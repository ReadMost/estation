package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"seats_id", "carriage_id", "user_id", "train_id"}))
public class Ticket implements java.io.Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", document_id=" + document_id +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", carriage=" + carriage +
                ", user=" + user +
                ", seat=" + seat +
                ", from=" + from +
                ", to=" + to +
                ", train=" + train +
                ", status='" + status + '\'' +
                '}';
    }
    @JsonIgnore
    public int getSeat() {
        return seat.getId();
    }
    public int getSeat_number(){
        return seat.getNumber();
    }
    @JsonIgnore
    public Station getFrom() {
        return from;
    }

    public int getFrom_id(){
        return from.getId();
    }
    public String getFrom_name(){
        return from.getName();

    }

    @JsonIgnore
    public Station getTo() {
        return to;
    }

    public int getTo_id(){
        return to.getId();
    }
    public String getTo_name(){
        return to.getName();

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
//    @JsonIdentityReference(alwaysAsId = true)
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
    @JsonIdentityReference(alwaysAsId = true)
    private Seats seat;


    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="from_station_id")
    @JsonBackReference(value="from-get")
    @JsonIdentityReference(alwaysAsId = true)
    private Station from;

    @ManyToOne
    @JoinColumn(name="to_station_id")
    @JsonBackReference(value="to-get")
    @JsonIdentityReference(alwaysAsId = true)
    private Station to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id")
    @JsonBackReference(value="train-get")
    @JsonIdentityReference(alwaysAsId = true)
    private Train train;

    private String status;

    @JsonIgnore
    public Carriage getCarriage() {
        return carriage;
    }

    public int getCarriage_id(){
        return carriage.getId();
    }
    public int getCarriage_number(){
        return carriage.getNumber();
    }

    public User getUser() {
        return user;
    }
    @JsonIgnore
    public Train getTrain() {
        return train;
    }
    public int getTrain_id(){
        return train.getId();
    }
    public String getTrain_number(){
        return train.getNumber();
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
