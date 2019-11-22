package com.javahelps.restservice.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date payed;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getPayed() {
        return payed;
    }

    public void setPayed(Date payed) {
        this.payed = payed;
    }

    private double salary;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonBackReference(value="agent-history")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="agent_id", nullable = false)
    private Agent agent;

}
