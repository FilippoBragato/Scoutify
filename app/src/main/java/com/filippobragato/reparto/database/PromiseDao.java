package com.filippobragato.reparto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.Scout;

import java.util.List;

@Dao
public interface PromiseDao {
    @Insert
    void insert(Promise promise);

    @Delete
    void delete(Promise promise);

    @Delete
    void reset(List<Promise> promises);

    @Query("SELECT * FROM promise_scout_table WHERE scout_ID is :sID")
    Promise findByPromiseId(int sID);

    //Updates
    @Query("UPDATE promise_scout_table SET test0 = :sBool WHERE scout_ID = :sID")
    void updateTest0(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test1 = :sBool WHERE scout_ID = :sID")
    void updateTest1(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test2 = :sBool WHERE scout_ID = :sID")
    void updateTest2(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test3 = :sBool WHERE scout_ID = :sID")
    void updateTest3(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test4 = :sBool WHERE scout_ID = :sID")
    void updateTest4(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test5 = :sBool WHERE scout_ID = :sID")
    void updateTest5(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test6 = :sBool WHERE scout_ID = :sID")
    void updateTest6(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test7 = :sBool WHERE scout_ID = :sID")
    void updateTest7(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test8 = :sBool WHERE scout_ID = :sID")
    void updateTest8(int sID, boolean sBool);
    @Query("UPDATE promise_scout_table SET test9 = :sBool WHERE scout_ID = :sID")
    void updateTest9(int sID, boolean sBool);


    //get all
    @Query("SELECT * FROM promise_scout_table")
    List<Promise> getAll();

}
