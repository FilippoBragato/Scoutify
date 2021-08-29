package com.filippobragato.reparto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.backend.Speciality;

import java.util.List;

@Dao
public interface SpecialityDao {
    @Insert
    void insert(Speciality speciality);

    @Delete
    void delete(Speciality speciality);

    @Delete
    void reset(List<Speciality> specialities);

    @Query("SELECT * FROM speciality_scout_table WHERE scout_ID is :sID")
    List<Speciality> findByScoutId(int sID);

    //Updates
    @Query("UPDATE speciality_scout_table SET test0 = :sBool WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateTest0(int sID_scout, int sID_name, boolean sBool);
    @Query("UPDATE speciality_scout_table SET test1 = :sBool WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateTest1(int sID_scout, int sID_name, boolean sBool);
    @Query("UPDATE speciality_scout_table SET test2 = :sBool WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateTest2(int sID_scout, int sID_name, boolean sBool);
    @Query("UPDATE speciality_scout_table SET test3 = :sBool WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateTest3(int sID_scout, int sID_name, boolean sBool);
    @Query("UPDATE speciality_scout_table SET test4 = :sBool WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateTest4(int sID_scout, int sID_name, boolean sBool);
    @Query("UPDATE speciality_scout_table SET free = :sTest WHERE scout_ID = :sID_scout AND id_name = :sID_name")
    void updateFree(int sID_scout, int sID_name, String sTest);

    //get all
    @Query("SELECT * FROM speciality_scout_table")
    List<Speciality> getAll();
}
