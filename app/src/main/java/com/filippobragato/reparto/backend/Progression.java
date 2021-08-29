package com.filippobragato.reparto.backend;

import java.util.List;

public class Progression {
    private int scoutId;
    private Speciality[] specialities;
    private Promise promise;
    private SecondClass secondClass;
    private FirstClass firstClass;

    public Progression(int scoutId) {
        this.scoutId = scoutId;
        this.specialities = new Speciality[26];
        this.promise = new Promise(scoutId);
        this.secondClass = new SecondClass(scoutId);
        this.firstClass = new FirstClass(scoutId);
        this.specialities = new Speciality[26];
    }

    public Progression(int scoutId, Promise promise, SecondClass secondClass, FirstClass firstClass, List<Speciality> s) {
        this.scoutId = scoutId;
        this.promise = promise;
        this.secondClass = secondClass;
        this.firstClass = firstClass;
        this.specialities = new Speciality[26];
        for (Speciality spe: s) {
            specialities[spe.getId_name()]= spe;
        }
    }

    public int getScoutId() {
        return scoutId;
    }

    public Promise getPromise() {
        return promise;
    }

    public void setPromise(Promise promise) {
        this.promise = promise;
    }

    public SecondClass getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(SecondClass secondClass) {
        this.secondClass = secondClass;
    }

    public FirstClass getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(FirstClass firstClass) {
        this.firstClass = firstClass;
    }

    public boolean hasSecond() {
        return this.secondClass.isFinished();
    }

    public boolean hasPromise() {
        return this.promise.isFinished();
    }
}
