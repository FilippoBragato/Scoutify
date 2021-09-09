package com.filippobragato.reparto.progressionFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Promise;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PromiseFragment extends Fragment {

    private View view;
    private CheckBox[] checkBoxes;
    private Promise promise;
    private DocumentReference doc;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_promise, container, false);
        promise = (Promise) getArguments().getSerializable("promise");
        id = getArguments().getInt("id");
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        doc = db.collection("lissaro").document("summary")
                .collection("scout").document(Integer.toString(id))
                .collection("extra").document("progression");
        if(promise.getTest0()) checkBoxes[0].setChecked(true);
        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest0(true);
                }
                else{
                    promise.setTest0(false);
                }
            }
        });
        if(promise.getTest1()) checkBoxes[1].setChecked(true);
        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest1(true);
                }
                else{
                    promise.setTest1(false);
                }
                updateDB();
            }
        });
        if(promise.getTest2()) checkBoxes[2].setChecked(true);
        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest2(true);
                }
                else{
                    promise.setTest2(false);
                }
                updateDB();
            }
        });
        if(promise.getTest3()) checkBoxes[3].setChecked(true);
        checkBoxes[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest3(true);
                }
                else{
                    promise.setTest3(false);
                }
                updateDB();
            }
        });
        if(promise.getTest4()) checkBoxes[4].setChecked(true);
        checkBoxes[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest4(true);
                }
                else{
                    promise.setTest4(false);
                }
                updateDB();
            }
        });
        if(promise.getTest5()) checkBoxes[5].setChecked(true);
        checkBoxes[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest5(true);
                }
                else{
                    promise.setTest5(false);
                }
                updateDB();
            }
        });
        if(promise.getTest6()) checkBoxes[6].setChecked(true);
        checkBoxes[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest6(true);
                }
                else{
                    promise.setTest6(false);
                }
                updateDB();
            }
        });
        if(promise.getTest7()) checkBoxes[7].setChecked(true);
        checkBoxes[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest7(true);
                }
                else{
                    promise.setTest7(false);
                }
                updateDB();
            }
        });
        if(promise.getTest8()) checkBoxes[8].setChecked(true);
        checkBoxes[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest8(true);
                }
                else{
                    promise.setTest8(false);
                }
                updateDB();
            }
        });
        if(promise.getTest9()) checkBoxes[9].setChecked(true);
        checkBoxes[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    promise.setTest9(true);
                }
                else{
                    promise.setTest9(false);
                }
                updateDB();
            }
        });
        return view;
    }

    private void updateDB(){
        if(promise.isFinished()){
            FirebaseFirestore.getInstance().collection("lissaro").document("summary")
                    .collection("scout").document(Integer.toString(id)).update("vertical", 1);
        }
        doc.update("promise", this.promise);
    }
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBoxes = new CheckBox[10];

    }
}