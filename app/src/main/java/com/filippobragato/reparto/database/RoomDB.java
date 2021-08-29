package com.filippobragato.reparto.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.backend.Speciality;

@Database(entities = {Scout.class, Note.class, Promise.class, SecondClass.class, FirstClass.class, Speciality.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract ScoutDao scoutDao();
    public abstract NoteDao noteDao();
    public abstract PromiseDao promiseDao();
    public abstract SecondDao secondDao();
    public abstract FirstDao firstDao();
    public abstract SpecialityDao specialityDao();
}
