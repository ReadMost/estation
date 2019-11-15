package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seats")
public class Seats implements Serializable {

//    @Id
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="carriage_id")
//    private Carriage carriage;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setCarriage(Carriage carriage) {
//        this.carriage = carriage;
//    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
