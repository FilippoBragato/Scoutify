package com.filippobragato.reparto.progressionFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.database.PromiseDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.veiwModel.TestsViewModel;

public class PromiseFragment extends Fragment {

    private View view;
    private TestsViewModel testsViewModel;
    private CheckBox[] checkBoxes;
    private RoomDB database;
    private int scoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_promise, container, false);
        database = RoomDB.getInstance(view.getContext());
        scoutId = getArguments().getInt("scout_ID");
        PromiseDao promiseDao = database.promiseDao();
        Promise promise = promiseDao.findByPromiseId(scoutId);
        checkBoxes[0] = view.findViewById(R.id.promiseFirstTest);
        checkBoxes[1] = view.findViewById(R.id.promiseSecondTest);
        checkBoxes[2] = view.findViewById(R.id.promiseThirdTest);
        checkBoxes[3] = view.findViewById(R.id.promiseFourthTest);
        checkBoxes[4] = view.findViewById(R.id.promiseFifthTest);
        checkBoxes[5] = view.findViewById(R.id.promiseSixthTest);
        checkBoxes[6] = view.findViewById(R.id.promiseSeventhTest);
        checkBoxes[7] = view.findViewById(R.id.promiseEighthTest);
        checkBoxes[8] = view.findViewById(R.id.promiseNinthTest);
        checkBoxes[9] = view.findViewById(R.id.promiseTenthTest);

        if(promise.getTest0()) checkBoxes[0].setChecked(true);
        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest0(true);
                    promiseDao.updateTest0(scoutId, true);
                }
                else{
                    promise.setTest0(false);
                    promiseDao.updateTest0(scoutId, false);
                }
            }
        });
        if(promise.getTest1()) checkBoxes[1].setChecked(true);
        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest1(true);
                    promiseDao.updateTest1(scoutId, true);
                }
                else{
                    promise.setTest1(false);
                    promiseDao.updateTest1(scoutId, false);
                }
            }
        });
        if(promise.getTest2()) checkBoxes[2].setChecked(true);
        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest2(true);
                    promiseDao.updateTest2(scoutId, true);
                }
                else{
                    promise.setTest2(false);
                    promiseDao.updateTest2(scoutId, false);
                }
            }
        });
        if(promise.getTest3()) checkBoxes[3].setChecked(true);
        checkBoxes[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest3(true);
                    promiseDao.updateTest3(scoutId, true);
                }
                else{
                    promise.setTest3(false);
                    promiseDao.updateTest3(scoutId, false);
                }
            }
        });
        if(promise.getTest4()) checkBoxes[4].setChecked(true);
        checkBoxes[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest4(true);
                    promiseDao.updateTest4(scoutId, true);
                }
                else{
                    promise.setTest4(false);
                    promiseDao.updateTest4(scoutId, false);
                }
            }
        });
        if(promise.getTest5()) checkBoxes[5].setChecked(true);
        checkBoxes[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest5(true);
                    promiseDao.updateTest5(scoutId, true);
                }
                else{
                    promise.setTest5(false);
                    promiseDao.updateTest5(scoutId, false);
                }
            }
        });
        if(promise.getTest6()) checkBoxes[6].setChecked(true);
        checkBoxes[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest6(true);
                    promiseDao.updateTest6(scoutId, true);
                }
                else{
                    promise.setTest6(false);
                    promiseDao.updateTest6(scoutId, false);
                }
            }
        });
        if(promise.getTest7()) checkBoxes[7].setChecked(true);
        checkBoxes[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest7(true);
                    promiseDao.updateTest7(scoutId, true);
                }
                else{
                    promise.setTest7(false);
                    promiseDao.updateTest7(scoutId, false);
                }
            }
        });
        if(promise.getTest8()) checkBoxes[8].setChecked(true);
        checkBoxes[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest8(true);
                    promiseDao.updateTest8(scoutId, true);
                }
                else{
                    promise.setTest8(false);
                    promiseDao.updateTest8(scoutId, false);
                }
            }
        });
        if(promise.getTest9()) checkBoxes[9].setChecked(true);
        checkBoxes[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest9(true);
                    promiseDao.updateTest9(scoutId, true);
                }
                else{
                    promise.setTest9(false);
                    promiseDao.updateTest9(scoutId, false);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBoxes = new CheckBox[10];

    }
}