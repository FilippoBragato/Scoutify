package com.filippobragato.reparto.backend;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.filippobragato.reparto.database.DateConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "scout_table")
public class Scout{
    // id for Room Database
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @TypeConverters(DateConverter.class)
    private Date birthDay;
    private String patrol;
    private String role;
    private boolean gone;
    private String imageUri;
    @Ignore
    private Progression progression;
    @Ignore
    private List<Note> notes;

    @Ignore
    public Scout(int id, String name, Date birthDay, String patrol, String role, boolean gone, Progression progression, List<Note> notes, String imageUri) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.patrol = patrol;
        this.role = role;
        this.gone = gone;
        this.progression = progression;
        this.notes = notes;
        this.imageUri = imageUri;
    }

    public Scout(String name, Date birthDay, String patrol, String role, String imageUri) {
        this.name = name;
        this.birthDay = birthDay;
        this.patrol = patrol;
        this.role = role;
        this.imageUri = imageUri;
        this.progression = new Progression(this.id);
        this.notes = new ArrayList<Note>();
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGone() {
        return gone;
    }

    public void setGone(boolean gone) {
        this.gone = gone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPatrol() {
        return patrol;
    }

    public void setPatrol(String patrol) {
        this.patrol = patrol;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    public void addNote(@NonNull String text) {
        if (!text.equals("")) {
            Note note = new Note(text, this.id);
            notes.add(note);
        }
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public boolean isAlive() {
        return !this.gone;
    }
}
