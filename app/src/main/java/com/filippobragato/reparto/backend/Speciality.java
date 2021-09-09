package com.filippobragato.reparto.backend;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
public class Speciality implements Serializable {
    private int id;
    private boolean test0 = false;
    private boolean test1 = false;
    private boolean test2 = false;
    private boolean test3 = false;
    private boolean test4 = false;
    private String free;

    public Speciality() {
    }

    public Speciality(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTest0() {
        return test0;
    }

    public void setTest0(boolean test0) {
        this.test0 = test0;
    }

    public boolean isTest1() {
        return test1;
    }

    public void setTest1(boolean test1) {
        this.test1 = test1;
    }

    public boolean isTest2() {
        return test2;
    }

    public void setTest2(boolean test2) {
        this.test2 = test2;
    }

    public boolean isTest3() {
        return test3;
    }

    public void setTest3(boolean test3) {
        this.test3 = test3;
    }

    public boolean isTest4() {
        return test4;
    }

    public void setTest4(boolean test4) {
        this.test4 = test4;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public boolean isFinished(){
        return test0 &&
                test1 &&
                test2 &&
                test3 &&
                test4;
    }
}
