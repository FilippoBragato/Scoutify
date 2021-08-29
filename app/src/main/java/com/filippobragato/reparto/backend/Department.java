package com.filippobragato.reparto.backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.filippobragato.reparto.database.NoteDao;
import com.filippobragato.reparto.database.PromiseDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.SecondDao;
import com.filippobragato.reparto.database.SpecialityDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Department {
    private ArrayList<Patrol> patrols;
    private ArrayList<Scout> gone;

    public ArrayList<Patrol> getPatrols() {
        return patrols;
    }

    public void setPatrols(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
    }

    public ArrayList<Scout> getGone() {
        return gone;
    }

    public void setGone(ArrayList<Scout> gone) {
        this.gone = gone;
    }

    public Department() {
        this.patrols = new ArrayList<>();
        this.gone = new ArrayList<>();
    }

    public Department(ArrayList<Patrol> patrols, ArrayList<Scout> gone) {
        this.patrols = patrols;
        this.gone = gone;
    }

    public void addScout(Scout scout){
        String patrolName = scout.getPatrol();
        for (Patrol patrol:
             this.patrols) {
            if(patrol.getName().equals(patrolName)) {
                patrol.addPatroller(scout);
                return;
            }
        }
        Patrol patrol = new Patrol(patrolName);
        patrol.addPatroller(scout);
        patrols.add(patrol);
    }

    public void updateScout(Scout scout) {
        String patrolName = scout.getPatrol();
        for (Iterator<Patrol> iterator = patrols.iterator(); iterator.hasNext();) {
            Patrol patrol = iterator.next();
            if (patrol.getName().equals(patrolName)){
                for(Iterator<Scout> iteratorScout = patrol.getPatrollers().iterator(); iteratorScout.hasNext();){
                    Scout s = iteratorScout.next();
                    if(s.getName().equals(scout.getName())){
                        iterator.remove();
                        patrol.addPatroller(scout);
                        return;
                    }
                }
            }
        }
    }

    public void removeScout(Scout scout, RoomDB database) {
        String patrolName = scout.getPatrol();
        int scoutId = scout.getId();
        for (Iterator<Patrol> iterator = patrols.iterator(); iterator.hasNext();) {
            Patrol patrol = iterator.next();
            if (patrol.getName().equals(patrolName)){
                patrol.removePatroller(scout);
                database.promiseDao().delete(scout.getProgression().getPromise());
                database.secondDao().delete(scout.getProgression().getSecondClass());
                database.firstDao().delete(scout.getProgression().getFirstClass());
                NoteDao noteDao = database.noteDao();
                noteDao.reset(noteDao.getNotesOf(scoutId));
                SpecialityDao specialityDao = database.specialityDao();
                specialityDao.reset(specialityDao.findByScoutId(scoutId));
                database.scoutDao().delete(scout);
                return;
            }
        }
    }

    public void setGone(Scout scout) {
        gone.add(scout);
        String patrolName = scout.getPatrol();
        for (Iterator<Patrol> iterator = patrols.iterator(); iterator.hasNext();) {
            Patrol patrol = iterator.next();
            if (patrol.getName().equals(patrolName)){
                patrol.removePatroller(scout);
                return;
            }
        }
    }
}
