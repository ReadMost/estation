package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="seats", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "carriage_id"}))
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Seats implements Serializable {

    @JsonBackReference(value="carriage-seat")
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


    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }


    @OneToMany(mappedBy = "seat",cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference(value="seat-get")
    private Set<Ticket> ticket=new HashSet<>(0);
}
