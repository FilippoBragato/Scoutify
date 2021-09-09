package com.filippobragato.reparto.backend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.filippobragato.reparto.database.DateConverter;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private Date date;
    private String text;

    public Note(){
    }

    public Note(String text) {
        this.date = new Date();
        this.text = text;
    }

    public Note(String text, Date date){
        this.date = date;
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
