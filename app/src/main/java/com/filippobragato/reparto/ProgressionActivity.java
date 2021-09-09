package com.filippobragato.reparto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.RecViewAdapter.SpecialityRecViewAdapter;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.progressionFragment.FirstClassFragment;
import com.filippobragato.reparto.progressionFragment.PromiseFragment;
import com.filippobragato.reparto.progressionFragment.SecondClassFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProgressionActivity extends AppCompatActivity {
    private Progression progression;
    private RecyclerView specialityRecyclerView;
    private SpecialityRecViewAdapter adapter;
    private int id;
    private DocumentReference doc;

    private void initializeProgression(){
        id = getIntent().getIntExtra("scout_ID", -1);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        doc = db.collection("lissaro").document("summary")
                .collection("scout").document(Integer.toString(id))
                .collection("extra").document("progression");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        progression = documentSnapshot.toObject(Progression.class);
                        // SET FRAGMENT
                        Bundle bundle = new Bundle();
                        if (progression.hasSecond()) {
                            bundle.putSerializable("first", progression.getFirstClass());
                            replaceFragment(new FirstClassFragment(), bundle);
                        } else {
                            if (progression.hasPromise()) {
                                bundle.putSerializable("second", progression.getSecondClass());
                                replaceFragment(new SecondClassFragment(), bundle);
                            } else {
                                bundle.putSerializable("promise", progression.getPromise());
                                replaceFragment(new PromiseFragment(), bundle);
                            }
                        }
                        //SET REC VIEW
                        specialityRecyclerView = findViewById(R.id.horizontalProgression);
                        String[] name = getResources().getStringArray(R.array.specialitiesName_array);
                        String[] tests = getResources().getStringArray(R.array.specialityTest_array);
                        adapter = new SpecialityRecViewAdapter(progression.getSpecialities(), name, tests, id);
                        specialityRecyclerView.setAdapter(adapter);
                        specialityRecyclerView.setLayoutManager(new LinearLayoutManager(ProgressionActivity.this, LinearLayoutManager.VERTICAL, false));
                        specialityRecyclerView.addItemDecoration(new DividerItemDecoration(specialityRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
                        specialityRecyclerView.setNestedScrollingEnabled(false);

                        //SET BUTTON
                        FloatingActionButton add = findViewById(R.id.floatingAddSpeciality);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int[] current = new int[progression.getSpecialities().size()];
                                for (int i = 0; i < progression.getSpecialities().size(); i++) {
                                    current[i] = progression.getSpecialities().get(i).getId();
                                }
                                startActivity(new Intent(ProgressionActivity.this, AddSpecialityActivity.class)
                                        .putExtra("current", current).putExtra("scout_ID", id));
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);
        initializeProgression();
    }

    @Override
    protected void onRestart() {
        initializeProgression();
        adapter.setSpecialities(this.progression.getSpecialities());
        super.onRestart();
    }

    private void replaceFragment(Fragment fragment, Bundle bundle) {
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVerticalProgression, fragment);
        fragmentTransaction.commit();
    }
}
