package com.filippobragato.reparto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.backend.Score;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insert(Score score);

    @Delete
    void delete(Score score);

    @Delete
    void reset(List<Score> scores);

    @Query("SELECT * FROM scout_score_table WHERE scout_id is :sScoutID and field is :sField")
    List<Score> getScores(int sScoutID, int sField);

    @Query("SELECT * FROM scout_score_table")
    List<Score> getAll();
}
