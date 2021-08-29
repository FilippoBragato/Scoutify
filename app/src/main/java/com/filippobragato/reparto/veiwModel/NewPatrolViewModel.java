package com.filippobragato.reparto.veiwModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewPatrolViewModel extends ViewModel {
    private final MutableLiveData<String> patrolName = new MutableLiveData<String>();

    public void setPatrolName(String patrolName) {
        this.patrolName.setValue(patrolName);
    }
    public LiveData<String> getPatrolName() {
        return patrolName;
    }
}