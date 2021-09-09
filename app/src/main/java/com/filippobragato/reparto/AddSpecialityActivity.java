package com.filippobragato.reparto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.RecViewAdapter.ClickableRecViewAdapter;
import com.filippobragato.reparto.backend.Speciality;
import com.filippobragato.reparto.database.RoomDB;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddSpecialityActivity extends AppCompatActivity implements ClickableRecViewAdapter.OnElementListener {
    private RecyclerView clickableRecView;
    private ArrayList<String> specialitiesName;
    private String[] allSpecialities;
    private int scoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speciality);
        scoutId = getIntent().getIntExtra("scout_ID", -1);
        allSpecialities = getResources().getStringArray(R.array.specialitiesName_array);
        specialitiesName = new ArrayList<>();
        int j = 0;
        int[] excluded = getIntent().getIntArrayExtra("current");
        for (int i = 0; i < allSpecialities.length; i++) {
            if(excluded.length > j && i == excluded[j]) j++;
            else specialitiesName.add(allSpecialities[i]);
        }
        ClickableRecViewAdapter adapter = new ClickableRecViewAdapter(specialitiesName, this);
        clickableRecView = findViewById(R.id.pickSpecialityRecView);
        clickableRecView.setAdapter(adapter);
        clickableRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        clickableRecView.addItemDecoration(new DividerItemDecoration(clickableRecView.getContext(), DividerItemDecoration.VERTICAL));


    }

    @Override
    public void onElementClick(int position) {
        int out = -1;
        for (int i = 0; i < allSpecialities.length; i++) {
            if (allSpecialities[i].equals(specialitiesName.get(position))){
                out = i;
                break;
            }
        }
        FirebaseFirestore.getInstance().collection("lissaro").document("summary")
                .collection("scout").document(Integer.toString(scoutId))
                .collection("extra").document("progression").update("specialities", FieldValue.arrayUnion(new Speciality(out)));
        finish();
    }
}