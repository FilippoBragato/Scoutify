package com.filippobragato.reparto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.filippobragato.reparto.backend.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Delete
    void reset(List<Note> notes);

    @Query("SELECT * FROM scout_note_table WHERE scout_id is :sScoutID ORDER BY date")
    List<Note> getNotesOf(int sScoutID);

    @Query("SELECT * FROM scout_note_table")
    List<Note> getAll();
}
