package com.filippobragato.reparto.backend;

import com.filippobragato.reparto.database.RoomDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Department {
    private ArrayList<Patrol> patrols;
    private ArrayList<Promise> promises;
    private ArrayList<SecondClass> secondClasses;
    private ArrayList<FirstClass> firstClasses;
    private ArrayList<Note> notes;
    private ArrayList<Score> scores;

    public ArrayList<Patrol> getPatrols() {
        return patrols;
    }

    public void setPatrols(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
    }

    public Department(List<Scout> scouts, List<Promise> promises, List<SecondClass> secondClasses, List<FirstClass> firstClasses, List<Note> notes, List<Score> scores) {
        this.patrols = new ArrayList<Patrol>();
        for (Scout scout: scouts) {
            this.addScout(scout);
        }
        this.promises = new ArrayList<Promise>();
        this.promises.addAll(promises);
        this.secondClasses = new ArrayList<SecondClass>();
        this.secondClasses.addAll(secondClasses);
        this.firstClasses = new ArrayList<FirstClass>();
        this.firstClasses.addAll(firstClasses);
        this.notes = new ArrayList<Note>();
        this.notes.addAll(notes);
        this.scores = new ArrayList<Score>();
        this.scores.addAll(scores);
    }

    public ArrayList<Promise> getPromises() {
        return promises;
    }

    public void setPromises(ArrayList<Promise> promises) {
        this.promises = promises;
    }

    public ArrayList<SecondClass> getSecondClasses() {
        return secondClasses;
    }

    public void setSecondClasses(ArrayList<SecondClass> secondClasses) {
        this.secondClasses = secondClasses;
    }

    public ArrayList<FirstClass> getFirstClasses() {
        return firstClasses;
    }

    public void setFirstClasses(ArrayList<FirstClass> firstClasses) {
        this.firstClasses = firstClasses;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public Department() {
        this.patrols = new ArrayList<>();
    }

    public Department(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
    }

    public void addScout(Scout scout){
        String patrolName = scout.getPatrol();
        for (Patrol patrol:
             this.patrols) {
            if(patrol.getName().equals(patrolName)) {
                patrol.addPatroller(scout);
                return;
            }
        }
        Patrol patrol = new Patrol(patrolName);
        patrol.addPatroller(scout);
        patrols.add(patrol);
    }

    public void updateScout(Scout scout) {
        String patrolName = scout.getPatrol();
        for (Iterator<Patrol> iterator = patrols.iterator(); iterator.hasNext();) {
            Patrol patrol = iterator.next();
            if (patrol.getName().equals(patrolName)){
                for(Iterator<Scout> iteratorScout = patrol.getPatrollers().iterator(); iteratorScout.hasNext();){
                    Scout s = iteratorScout.next();
                    if(s.getName().equals(scout.getName())){
                        iterator.remove();
                        patrol.addPatroller(scout);
                        return;
                    }
                }
            }
        }
    }

    public void removeScout(Scout scout) {
        String patrolName = scout.getPatrol();
        int scoutId = scout.getId();
        for (Iterator<Patrol> iterator = patrols.iterator(); iterator.hasNext();) {
            Patrol patrol = iterator.next();
            if (patrol.getName().equals(patrolName)){
                patrol.removePatroller(scout);
                return;
            }
        }
    }
}
