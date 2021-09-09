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
public class Scout {
    // id for Room Database
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @Ignore
    private Date birthDay;
    private String patrol;
    private String role;
    private boolean gone;
    private String imageUri;
    @Ignore
    private int vertical;
    @Ignore
    private Score score;
    @Ignore
    private List<String> specialities;

    @Ignore
    public Scout(String name, Date birthday, String patrol, String role, String imageUri) {
        this.name = name;
        this.birthDay = birthday;
        this.patrol = patrol;
        this.role = role;
        this.imageUri = imageUri;
        this.specialities = new ArrayList<>();
        this.score = new Score();
    }

    @Ignore
    public Scout() {
        this.specialities = new ArrayList<>();
        this.score = new Score();
    }

    @Ignore
    public Scout(int id, String name, Date birthDay, String patrol, String role, boolean gone, String imageUri) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.patrol = patrol;
        this.role = role;
        this.gone = gone;
        this.imageUri = imageUri;
        this.specialities = new ArrayList<>();
        this.score = new Score();
    }

    public Scout(int id, String name, String patrol, String role, boolean gone, String imageUri) {
        this.id = id;
        this.name = name;
        this.patrol = patrol;
        this.role = role;
        this.gone = gone;
        this.imageUri = imageUri;
    }

    @Ignore
    public Scout(int id, String name, String patrol, String role, boolean gone) {
        this.id = id;
        this.name = name;
        this.patrol = patrol;
        this.role = role;
        this.specialities = new ArrayList<>();
        this.score = new Score();
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public List<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
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
}
