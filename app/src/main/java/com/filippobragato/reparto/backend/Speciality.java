package com.filippobragato.reparto.backend;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "speciality_scout_table", primaryKeys = {"scout_ID", "id_name"})
public class Speciality implements Serializable {
    private int scout_ID;
    private int id_name;
    private boolean test0 = false;
    private boolean test1 = false;
    private boolean test2 = false;
    private boolean test3 = false;
    private boolean test4 = false;
    private String free;

    @Ignore
    public Speciality(int scout_ID, int id_name) {
        this.scout_ID = scout_ID;
        this.id_name = id_name;
    }

    public Speciality(int scout_ID, int id_name, boolean test0, boolean test1, boolean test2, boolean test3, boolean test4, String free) {
        this.scout_ID = scout_ID;
        this.id_name = id_name;
        this.test0 = test0;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
        this.free = free;
    }

    public int getScout_ID() {
        return scout_ID;
    }

    public void setScout_ID(int scout_ID) {
        this.scout_ID = scout_ID;
    }

    public int getId_name() {
        return id_name;
    }

    public void setId_name(int id_name) {
        this.id_name = id_name;
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
}
