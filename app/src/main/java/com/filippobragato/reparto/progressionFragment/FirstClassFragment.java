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

import com.filippobragato.reparto.ProgressionActivity;
import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.database.FirstDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.veiwModel.TestsViewModel;

public class FirstClassFragment extends Fragment {

    private View view;
    private TestsViewModel testsViewModel;
    private CheckBox[] checkBoxes;
    private RoomDB database;
    private int scoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_first_class, container, false);
        database = RoomDB.getInstance(view.getContext());
        scoutId = getArguments().getInt("scout_ID");
        FirstDao firstDao = database.firstDao();
        FirstClass firstClass = firstDao.findByFirstClassId(scoutId);
        checkBoxes[0] = view.findViewById(R.id.firstFirstTest);
        checkBoxes[1] = view.findViewById(R.id.firstSecondTest);
        checkBoxes[2] = view.findViewById(R.id.firstThirdTest);
        checkBoxes[3] = view.findViewById(R.id.firstFourthTest);
        checkBoxes[4] = view.findViewById(R.id.firstFifthTest);
        checkBoxes[5] = view.findViewById(R.id.firstSixthTest);
        checkBoxes[6] = view.findViewById(R.id.firstSeventhTest);
        checkBoxes[7] = view.findViewById(R.id.firstEighthTest);
        checkBoxes[8] = view.findViewById(R.id.firstNinthTest);
        checkBoxes[9] = view.findViewById(R.id.firstTenthTest);
        checkBoxes[10] = view.findViewById(R.id.firstEleventhTest);
        checkBoxes[11] = view.findViewById(R.id.firstTwelfthTest);

        if(firstClass.getTest0()) checkBoxes[0].setChecked(true);
        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest0(true);
                    firstDao.updateTest0(scoutId, true);
                }
                else{
                    firstClass.setTest0(false);
                    firstDao.updateTest0(scoutId, false);
                }
            }
        });
        if(firstClass.getTest1()) checkBoxes[1].setChecked(true);
        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest1(true);
                    firstDao.updateTest1(scoutId, true);
                }
                else{
                    firstClass.setTest1(false);
                    firstDao.updateTest1(scoutId, false);
                }
            }
        });
        if(firstClass.getTest2()) checkBoxes[2].setChecked(true);
        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest2(true);
                    firstDao.updateTest2(scoutId, true);
                }
                else{
                    firstClass.setTest2(false);
                    firstDao.updateTest2(scoutId, false);
                }
            }
        });
        if(firstClass.getTest3()) checkBoxes[3].setChecked(true);
        checkBoxes[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest3(true);
                    firstDao.updateTest3(scoutId, true);
                }
                else{
                    firstClass.setTest3(false);
                    firstDao.updateTest3(scoutId, false);
                }
            }
        });
        if(firstClass.getTest4()) checkBoxes[4].setChecked(true);
        checkBoxes[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest4(true);
                    firstDao.updateTest4(scoutId, true);
                }
                else{
                    firstClass.setTest4(false);
                    firstDao.updateTest4(scoutId, false);
                }
            }
        });
        if(firstClass.getTest5()) checkBoxes[5].setChecked(true);
        checkBoxes[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest5(true);
                    firstDao.updateTest5(scoutId, true);
                }
                else{
                    firstClass.setTest5(false);
                    firstDao.updateTest5(scoutId, false);
                }
            }
        });
        if(firstClass.getTest6()) checkBoxes[6].setChecked(true);
        checkBoxes[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest6(true);
                    firstDao.updateTest6(scoutId, true);
                }
                else{
                    firstClass.setTest6(false);
                    firstDao.updateTest6(scoutId, false);
                }
            }
        });
        if(firstClass.getTest7()) checkBoxes[7].setChecked(true);
        checkBoxes[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest7(true);
                    firstDao.updateTest7(scoutId, true);
                }
                else{
                    firstClass.setTest7(false);
                    firstDao.updateTest7(scoutId, false);
                }
            }
        });
        if(firstClass.getTest8()) checkBoxes[8].setChecked(true);
        checkBoxes[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest8(true);
                    firstDao.updateTest8(scoutId, true);
                }
                else{
                    firstClass.setTest8(false);
                    firstDao.updateTest8(scoutId, false);
                }
            }
        });
        if(firstClass.getTest9()) checkBoxes[9].setChecked(true);
        checkBoxes[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest9(true);
                    firstDao.updateTest9(scoutId, true);
                }
                else{
                    firstClass.setTest9(false);
                    firstDao.updateTest9(scoutId, false);
                }
            }
        });
        if(firstClass.getTest10()) checkBoxes[10].setChecked(true);
        checkBoxes[10].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest10(true);
                    firstDao.updateTest10(scoutId, true);
                }
                else{
                    firstClass.setTest10(false);
                    firstDao.updateTest10(scoutId, false);
                }
            }
        });
        if(firstClass.getTest11()) checkBoxes[11].setChecked(true);
        checkBoxes[11].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    firstClass.setTest11(true);
                    firstDao.updateTest11(scoutId, true);
                }
                else{
                    firstClass.setTest11(false);
                    firstDao.updateTest11(scoutId, false);
                }
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBoxes = new CheckBox[12];

    }
}