package com.filippobragato.reparto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.RecViewAdapter.SpecialityRecViewAdapter;
import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.backend.Speciality;
import com.filippobragato.reparto.database.FirstDao;
import com.filippobragato.reparto.database.PromiseDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.SecondDao;
import com.filippobragato.reparto.database.SpecialityDao;
import com.filippobragato.reparto.progressionFragment.FirstClassFragment;
import com.filippobragato.reparto.progressionFragment.PromiseFragment;
import com.filippobragato.reparto.progressionFragment.SecondClassFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProgressionActivity extends AppCompatActivity {
    private Progression progression;
    private List<Speciality> specialities;
    private RecyclerView specialityRecyclerView;
    private SpecialityRecViewAdapter adapter;
    private RoomDB database;
    private int id;


    private void initializeProgression(){
        PromiseDao promiseDao = database.promiseDao();
        SecondDao secondDao = database.secondDao();
        FirstDao firstDao = database.firstDao();
        SpecialityDao specialityDao = database.specialityDao();
        id = getIntent().getIntExtra("scout_ID", -1);
        specialities = specialityDao.findByScoutId(id);
        Promise promise = promiseDao.findByPromiseId(id);
        SecondClass secondClass = secondDao.findBySecondClassId(id);
        FirstClass firstClass = firstDao.findByFirstClassId(id);
        progression = new Progression(id, promise,secondClass,firstClass,specialities);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);
        database = RoomDB.getInstance(this);
        initializeProgression();

        // SET FRAGMENT
        if(progression.hasSecond()){
            replaceFragment(new FirstClassFragment());
        }
        else{
            if(progression.hasPromise()){
                replaceFragment(new SecondClassFragment());
            }
            else{
                replaceFragment(new PromiseFragment());
            }
        }

        //SET REC VIEW
        specialityRecyclerView = findViewById(R.id.horizontalProgression);
        String[] name = getResources().getStringArray(R.array.specialitiesName_array);
        String[] tests = getResources().getStringArray(R.array.specialityTest_array);
        adapter = new SpecialityRecViewAdapter(specialities, name, tests);
        specialityRecyclerView.setAdapter(adapter);
        specialityRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        specialityRecyclerView.addItemDecoration(new DividerItemDecoration(specialityRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //SET BUTTON
        FloatingActionButton add = findViewById(R.id.floatingAddSpeciality);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] current = new int[specialities.size()];
                for (int i = 0; i < specialities.size(); i++) {
                    current[i] = specialities.get(i).getId_name();
                }
                startActivity(new Intent(ProgressionActivity.this, AddSpecialityActivity.class)
                        .putExtra("current", current).putExtra("scout_ID", id));
            }
        });

    }

    @Override
    protected void onRestart() {
        initializeProgression();
        adapter.setSpecialities(this.specialities);
        super.onRestart();
    }

    private void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putInt("scout_ID", id);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVerticalProgression, fragment);
        fragmentTransaction.commit();
    }
}
