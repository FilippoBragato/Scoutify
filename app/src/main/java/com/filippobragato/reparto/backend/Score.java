package com.filippobragato.reparto.backend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scout_score_table")
public class Score {
    @PrimaryKey(autoGenerate = true)
    private int uID;
    @ColumnInfo(name = "scout_id")
    private int scoutId;
    private int field;
    private double score;

    public Score(int uID, int scoutId, int field, double score) {
        this.uID = uID;
        this.scoutId = scoutId;
        this.field = field;
        this.score = score;
    }

    public int getUID() {
        return uID;
    }

    public void setUID(int uID) {
        this.uID = uID;
    }

    public int getScoutId() {
        return scoutId;
    }

    public void setScoutId(int scoutId) {
        this.scoutId = scoutId;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
