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

import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.SecondDao;
import com.filippobragato.reparto.veiwModel.TestsViewModel;

public class SecondClassFragment extends Fragment {

    private View view;
    private TestsViewModel testsViewModel;
    private CheckBox[] checkBoxes;
    private RoomDB database;
    private int scoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_second_class, container, false);
        database = RoomDB.getInstance(view.getContext());
        scoutId = getArguments().getInt("scout_ID");
        SecondDao secondDao = database.secondDao();
        SecondClass secondClass = secondDao.findBySecondClassId(scoutId);
        checkBoxes[0] = view.findViewById(R.id.secondFirstTest);
        checkBoxes[1] = view.findViewById(R.id.secondSecondTest);
        checkBoxes[2] = view.findViewById(R.id.secondThirdTest);
        checkBoxes[3] = view.findViewById(R.id.secondFourthTest);
        checkBoxes[4] = view.findViewById(R.id.secondFifthTest);
        checkBoxes[5] = view.findViewById(R.id.secondSixthTest);
        checkBoxes[6] = view.findViewById(R.id.secondSeventhTest);
        checkBoxes[7] = view.findViewById(R.id.secondEighthTest);
        checkBoxes[8] = view.findViewById(R.id.secondNinthTest);
        checkBoxes[9] = view.findViewById(R.id.secondTenthTest);
        checkBoxes[10] = view.findViewById(R.id.secondEleventhTest);
        checkBoxes[11] = view.findViewById(R.id.secondTwelfthTest);
        checkBoxes[12] = view.findViewById(R.id.secondThirteenthTest);
        if(secondClass.getTest0()) checkBoxes[0].setChecked(true);
        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest0(true);
                    secondDao.updateTest0(scoutId, true);
                }
                else{
                    secondClass.setTest0(false);
                    secondDao.updateTest0(scoutId, false);
                }
            }
        });
        if(secondClass.getTest1()) checkBoxes[1].setChecked(true);
        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest1(true);
                    secondDao.updateTest1(scoutId, true);
                }
                else{
                    secondClass.setTest1(false);
                    secondDao.updateTest1(scoutId, false);
                }
            }
        });
        if(secondClass.getTest2()) checkBoxes[2].setChecked(true);
        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest2(true);
                    secondDao.updateTest2(scoutId, true);
                }
                else{
                    secondClass.setTest2(false);
                    secondDao.updateTest2(scoutId, false);
                }
            }
        });
        if(secondClass.getTest3()) checkBoxes[3].setChecked(true);
        checkBoxes[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest3(true);
                    secondDao.updateTest3(scoutId, true);
                }
                else{
                    secondClass.setTest3(false);
                    secondDao.updateTest3(scoutId, false);
                }
            }
        });
        if(secondClass.getTest4()) checkBoxes[4].setChecked(true);
        checkBoxes[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest4(true);
                    secondDao.updateTest4(scoutId, true);
                }
                else{
                    secondClass.setTest4(false);
                    secondDao.updateTest4(scoutId, false);
                }
            }
        });
        if(secondClass.getTest5()) checkBoxes[5].setChecked(true);
        checkBoxes[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest5(true);
                    secondDao.updateTest5(scoutId, true);
                }
                else{
                    secondClass.setTest5(false);
                    secondDao.updateTest5(scoutId, false);
                }
            }
        });
        if(secondClass.getTest6()) checkBoxes[6].setChecked(true);
        checkBoxes[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest6(true);
                    secondDao.updateTest6(scoutId, true);
                }
                else{
                    secondClass.setTest6(false);
                    secondDao.updateTest6(scoutId, false);
                }
            }
        });
        if(secondClass.getTest7()) checkBoxes[7].setChecked(true);
        checkBoxes[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest7(true);
                    secondDao.updateTest7(scoutId, true);
                }
                else{
                    secondClass.setTest7(false);
                    secondDao.updateTest7(scoutId, false);
                }
            }
        });
        if(secondClass.getTest8()) checkBoxes[8].setChecked(true);
        checkBoxes[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest8(true);
                    secondDao.updateTest8(scoutId, true);
                }
                else{
                    secondClass.setTest8(false);
                    secondDao.updateTest8(scoutId, false);
                }
            }
        });
        if(secondClass.getTest9()) checkBoxes[9].setChecked(true);
        checkBoxes[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest9(true);
                    secondDao.updateTest9(scoutId, true);
                }
                else{
                    secondClass.setTest9(false);
                    secondDao.updateTest9(scoutId, false);
                }
            }
        });
        if(secondClass.getTest10()) checkBoxes[10].setChecked(true);
        checkBoxes[10].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest10(true);
                    secondDao.updateTest10(scoutId, true);
                }
                else{
                    secondClass.setTest10(false);
                    secondDao.updateTest10(scoutId, false);
                }
            }
        });
        if(secondClass.getTest11()) checkBoxes[11].setChecked(true);
        checkBoxes[11].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest11(true);
                    secondDao.updateTest11(scoutId, true);
                }
                else{
                    secondClass.setTest11(false);
                    secondDao.updateTest11(scoutId, false);
                }
            }
        });
        if(secondClass.getTest12()) checkBoxes[12].setChecked(true);
        checkBoxes[12].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    secondClass.setTest12(true);
                    secondDao.updateTest12(scoutId, true);
                }
                else{
                    secondClass.setTest12(false);
                    secondDao.updateTest12(scoutId, false);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBoxes = new CheckBox[13];

    }
}