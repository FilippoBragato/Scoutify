package com.filippobragato.reparto.backend;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class SecondClass implements Serializable {

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
    private boolean test10 = false;
    private boolean test11 = false;
    private boolean test12 = false;
    private String free;

    public SecondClass() {
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

    public boolean getTest10() {
        return test10;
    }

    public void setTest10(boolean test10) {
        this.test10 = test10;
    }

    public boolean getTest11() {
        return test11;
    }

    public void setTest11(boolean test11) {
        this.test11 = test11;
    }

    public boolean getTest12() {
        return test12;
    }

    public void setTest12(boolean test12) {
        this.test12 = test12;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
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
                test9 &&
                test10 &&
                test11 &&
                test12;
    }
    public void setFinished(){
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
        this.test10 = true;
        this.test11 = true;
        this.test12 = true;
    }
}
