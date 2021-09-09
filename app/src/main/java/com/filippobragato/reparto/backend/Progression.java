package com.filippobragato.reparto.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Progression{
    private List<Speciality> specialities;
    private Promise promise;
    private SecondClass secondClass;
    private FirstClass firstClass;

    public Progression() {
        this.promise = new Promise();
        this.secondClass = new SecondClass();
        this.firstClass = new FirstClass();
        this.specialities = new ArrayList<>();
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
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
