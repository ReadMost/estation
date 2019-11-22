package com.javahelps.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="adjustment")
public class Adjustment {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    public Date getAdjustedDate() {
        return adjustedDate;
    }

    public void setAdjustedDate(Date adjustedDate) {
        this.adjustedDate = adjustedDate;
    }

    private Date adjustedDate;

    public long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }

    private long totalHours;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @JsonBackReference(value="agent-adjustment")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="agent_id", nullable = false)
    private Agent agent;


    public Time getFr() {
        return fr;
    }

    public void setFr(Time fr) {
        this.fr = fr;
    }

    private Time fr;

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    private Time t;
}
