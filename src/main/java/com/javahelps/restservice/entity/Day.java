package com.javahelps.restservice.entity;



import javax.persistence.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="day")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "type_day",
            joinColumns = { @JoinColumn(name = "day_id") },
            inverseJoinColumns = { @JoinColumn(name = "type_id") }
    )
    private Set<Type> type=new HashSet<>();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "agent_day",
            joinColumns = { @JoinColumn(name = "day_id") },
            inverseJoinColumns = { @JoinColumn(name = "agent_id") }
    )
    private  Set<Agent> agent=new HashSet<>();



}
