package com.javahelps.restservice.entity;

import javax.persistence.*;
@Entity
@Table(name = "seats")
public class Seats {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;

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


}
