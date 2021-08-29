package com.filippobragato.reparto.backend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.filippobragato.reparto.database.DateConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "scout_note_table")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(DateConverter.class)
    final Date date;

    @ColumnInfo(name = "scout_id")
    private int scoutId;
    private String text;

    @Ignore
    public Note(String text, int scoutId) {
        this.scoutId = scoutId;
        this.date = new Date();
        this.text = text;
    }

    public Note(int id, Date date, int scoutId, String text) {
        this.id = id;
        this.date = date;
        this.scoutId = scoutId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScoutId() {
        return scoutId;
    }

    public void setScoutId(int scoutId) {
        this.scoutId = scoutId;
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
