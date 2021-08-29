package com.filippobragato.reparto.backend;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Struct;

@Entity(tableName = "promise_scout_table")
public class Promise {

    @PrimaryKey
    private int scout_ID;
    private boolean test0 = false;
    private boolean test1 = false;
    private boolean test2 = false;
    private boolean test3 = false;
    private boolean test4 = false;
    private boolean test5 = false;
    private boolean test6 = false;
    private boolean test7 = false;
    private boolean test8 = false;
    private boolean test9 = false;


    public Promise(int scout_ID) {
        this.scout_ID = scout_ID;
    }

    public Promise(int scoutId, boolean test0, boolean test1, boolean test2, boolean test3, boolean test4, boolean test5, boolean test6, boolean test7, boolean test8, boolean test9) {
        this.scout_ID = scoutId;
        this.test0 = test0;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
        this.test5 = test5;
        this.test6 = test6;
        this.test7 = test7;
        this.test8 = test8;
        this.test9 = test9;
    }

    public int getScout_ID() {
        return scout_ID;
    }

    public void setScout_ID(int scoutId) {
        this.scout_ID = scoutId;
    }

    public boolean getTest0() {
        return test0;
    }

    public void setTest0(boolean test0) {
        this.test0 = test0;
    }

    public boolean getTest1() {
        return test1;
    }

    public void setTest1(boolean test1) {
        this.test1 = test1;
    }

    public boolean getTest2() {
        return test2;
    }

    public void setTest2(boolean test2) {
        this.test2 = test2;
    }

    public boolean getTest3() {
        return test3;
    }

    public void setTest3(boolean test3) {
        this.test3 = test3;
    }

    public boolean getTest4() {
        return test4;
    }

    public void setTest4(boolean test4) {
        this.test4 = test4;
    }

    public boolean getTest5() {
        return test5;
    }

    public void setTest5(boolean test5) {
        this.test5 = test5;
    }

    public boolean getTest6() {
        return test6;
    }

    public void setTest6(boolean test6) {
        this.test6 = test6;
    }

    public boolean getTest7() {
        return test7;
    }

    public void setTest7(boolean test7) {
        this.test7 = test7;
    }

    public boolean getTest8() {
        return test8;
    }

    public void setTest8(boolean test8) {
        this.test8 = test8;
    }

    public boolean getTest9() {
        return test9;
    }

    public void setTest9(boolean test9) {
        this.test9 = test9;
    }

    public boolean isFinished(){
        return  test0 &&
                test1 &&
                test2 &&
                test3 &&
                test4 &&
                test5 &&
                test6 &&
                test7 &&
                test8 &&
                test9;
    }

    public void setFinished() {
        this.test0 = true;
        this.test1 = true;
        this.test2 = true;
        this.test3 = true;
        this.test4 = true;
        this.test5 = true;
        this.test6 = true;
        this.test7 = true;
        this.test8 = true;
        this.test9 = true;
    }
}
