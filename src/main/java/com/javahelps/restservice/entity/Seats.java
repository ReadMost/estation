package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seats", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "carriage_id"}))
public class Seats implements Serializable {

    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carriage_id")
    private Carriage carriage;

    public void setCarriage(Carriage carriage) {   this.carriage = carriage;   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {  return number; }

    public void setNumber(int number) {  this.number = number;    }


}
