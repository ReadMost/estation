package com.javahelps.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Ticket {

    private int id;
    private int price;
    private int document_id;
    private String lName;
    private String fName;
    private Seats seat;
    private Station from;
    private Station to;
    private Train train;
    private Carriage carriage;
    private String status;


}
