package com.filippobragato.reparto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.filippobragato.reparto.backend.Scout;

import java.util.Date;
import java.util.List;

@Dao
public interface ScoutDao {
    @Insert
    void insert(Scout scout);

    @Delete
    void delete(Scout scout);

    @Delete
    void reset(List<Scout> scouts);

    @Query("SELECT * FROM scout_table WHERE id is :sID")
    Scout findByScoutId(int sID);

    @Query("SELECT id FROM scout_table WHERE name = :sName AND patrol = :sPatrol AND role = :sRole")
    int getId(String sName, String sPatrol, String sRole);

    //Updates
    @Query("UPDATE scout_table SET name = :sName WHERE ID = :sID")
    void updateName(int sID, String sName);

    @Query("UPDATE scout_table SET patrol = :sPatrol WHERE ID = :sID")
    void updatePatrol(int sID, String sPatrol);

    @Query("UPDATE scout_table SET role = :sRole WHERE ID = :sID")
    void updateRole(int sID, String sRole);

    @Query("UPDATE scout_table SET imageUri = :sImage WHERE ID = :sID")
    void updateImage(int sID, String sImage);

    @Query("UPDATE scout_table SET gone = :sGone WHERE ID = :sID")
    void updateGone(int sID, Boolean sGone);



    //get all
    @Query("SELECT * FROM scout_table")
    List<Scout> getAll();

    @Query("SELECT * FROM scout_table WHERE gone is 0")
    List<Scout> getAllAlive();

    @Query("SELECT DISTINCT patrol FROM scout_table")
    List<String> getPatrols();

    @Query("SELECT * FROM scout_table WHERE role IS :sRole")
    List<Scout> getAllByRole(String sRole);

}
