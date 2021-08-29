package com.filippobragato.reparto.backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Patrol implements Serializable {
    private String name;
    private ArrayList<Scout> patrollers;

    public Patrol(String name, ArrayList<Scout> patrollers) {
        this.name = name;
        this.patrollers = patrollers;
    }

    public Patrol(String patrolName) {
        this.name = patrolName;
        patrollers = new ArrayList<Scout>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Scout> getPatrollers() {
        return patrollers;
    }

    public void setPatrollers(ArrayList<Scout> patrollers) {
        this.patrollers = patrollers;
    }

    public void addPatroller(Scout scout) {
        this.patrollers.add(scout);
    }

    public void removePatroller(Scout scout) {
        this.patrollers.remove(scout);
    }
}
