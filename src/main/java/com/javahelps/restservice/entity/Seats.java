package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="seats", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "carriage_id"}))
public class Seats implements Serializable {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="seat_id")
    private int id;

    private int number;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


//    public Set<Ticket> getTicket() {
//        return ticket;
//    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "seat",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Ticket> ticket=new HashSet<>(0);
}
