package com.filippobragato.reparto.veiwModel;

import android.content.ClipData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestsViewModel extends ViewModel {
    private final MutableLiveData<Boolean[]> tests = new MutableLiveData<Boolean[]>();

    public void setTests(Boolean[] tests) {
        this.tests.setValue(tests);
    }
    public LiveData<Boolean[]> getTests() {
        return tests;
    }
}
/*
    private final MutableLiveData<ClipData.Item> selected = new MutableLiveData<ClipData.Item>();

    public void select(ClipData.Item item) {
        selected.setValue(item);
    }

    public LiveData<ClipData.Item> getSelected() {
        return selected;
    }
}*/